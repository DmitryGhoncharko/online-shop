package by.onlineShop.service.impl;

import by.onlineShop.dao.DaoFactory;
import by.onlineShop.dao.impl.CategoryDaoImpl;
import by.onlineShop.entity.Category;
import by.onlineShop.exeptions.DaoException;
import by.onlineShop.exeptions.ServiceException;
import by.onlineShop.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Category> retrieveCategories() throws ServiceException {
        try {
            CategoryDaoImpl categoryDao = DaoFactory.getInstance().getCategoryDao();
            List<Category> result = null;
            result = categoryDao.findAll();
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve categories!");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Category> retrieveCategoryBtId(long categoryId) throws ServiceException {
        try {
            CategoryDaoImpl categoryDao = DaoFactory.getInstance().getCategoryDao();
            Optional<Category> result;
            result = categoryDao.findById(categoryId);
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve category by id!");
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
