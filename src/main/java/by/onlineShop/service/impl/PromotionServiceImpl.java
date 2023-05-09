package by.onlineShop.service.impl;

import by.onlineShop.dao.DaoFactory;
import by.onlineShop.dao.PromotionDao;
import by.onlineShop.dao.impl.PromotionDaoImpl;
import by.onlineShop.entity.Product;
import by.onlineShop.entity.Promotion;
import by.onlineShop.exeptions.DaoException;
import by.onlineShop.exeptions.ServiceException;
import by.onlineShop.service.PromotionService;
import by.onlineShop.service.validator.Validator;
import by.onlineShop.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PromotionServiceImpl implements PromotionService {
    private static final Logger logger = LogManager.getLogger();

    private static final int HUNDRED_PERCENT = 100;

    @Override
    public List<Promotion> retrievePromotions() throws ServiceException {
        try {
            PromotionDaoImpl promotionDao = DaoFactory.getInstance().getPromotionDao();
            List<Promotion> result = null;
            result = promotionDao.findAll();
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve promotions!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Promotion> retrievePromotionById(long promotionId) throws ServiceException {
        try {
            PromotionDaoImpl promotionDao = DaoFactory.getInstance().getPromotionDao();
            Optional<Promotion> result;
            result = promotionDao.findById(promotionId);
            if (!checkRelevanceOfPromotion(result)) {
                return Optional.empty();
            }
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve promotion by id!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Double calculateNewPrice(double price, int discount) {
        return price * (HUNDRED_PERCENT - discount) / HUNDRED_PERCENT;
    }

    @Override
    public Map<String, Double> getNewPrices(List<Product> products) throws ServiceException {
        Map<String, Double> newPrices = new HashMap<>();

        for (Product product : products) {
            if (product.getPromotionId() != 0) {
                Optional<Promotion> promotion = retrievePromotionById(product.getPromotionId());
                if (checkRelevanceOfPromotion(promotion)) {
                    Double newPrice = calculateNewPrice(product.getPrice(), promotion.get().getDiscount());
                    double scale = Math.pow(10, 2);
                    newPrice = Math.ceil(newPrice * scale) / scale;
                    newPrices.put(product.getName(), newPrice);
                }
            }
        }
        return newPrices;
    }

    @Override
    public boolean addNewPromotion(String promotionName, String photo, String beginningDateString, String expirationDateString,
                                   String description, String discountString) throws ServiceException {
        if (promotionName == null || photo == null || beginningDateString == null || expirationDateString == null ||
                description == null || discountString == null) {
            return false;
        }

        try {
            PromotionDao promotionDao = DaoFactory.getInstance().getPromotionDao();
            Optional<Promotion> existPromotion = promotionDao.findByName(promotionName);
            if (existPromotion.isPresent()) {
                return false;
            }

            Date beginningDate = new SimpleDateFormat("yyyy-MM-dd").parse(beginningDateString);
            Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDateString);

            if (!isPromotionDatesCorrect(beginningDate, expirationDate)) {
                return false;
            }

            Validator discountValidator = ValidatorFactory.getInstance().getDiscountValidator();
            if (!discountValidator.isValid(discountString)) {
                return false;
            }

            byte discount = Byte.parseByte(discountString);

            Promotion promotion = buildPromotion(promotionName, photo, beginningDate, expirationDate, discount, description);
            promotionDao.save(promotion);

            return true;
        } catch (DaoException | ParseException e) {
            logger.error("Unable to add product!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean checkRelevanceOfPromotion(Optional<Promotion> promotion) {
        if (promotion.isPresent()) {
            Date currentDate = new Date();
            return promotion.get().getExpirationDate().compareTo(currentDate) >= 0;
        }
        return false;
    }

    private boolean isPromotionDatesCorrect(Date beginningDate, Date expirationDate) {
        Date currentDate = new Date();
        if (!(beginningDate.compareTo(currentDate) >= 0)) {
            return false;
        }

        if (!(expirationDate.compareTo(beginningDate) >= 0)) {
            return false;
        }

        return true;
    }

    private Promotion buildPromotion(String name, String photo, Date beginningDate, Date expirationDate,
                                     byte discount, String description) {
        Promotion promotion = new Promotion();
        promotion.setName(name);
        promotion.setPhoto(photo);
        promotion.setBeginningDate(beginningDate);
        promotion.setExpirationDate(expirationDate);
        promotion.setDiscount(discount);
        promotion.setDescription(description);
        return promotion;
    }
}
