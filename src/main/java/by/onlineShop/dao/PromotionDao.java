package by.onlineShop.dao;

import by.onlineShop.entity.Promotion;
import by.onlineShop.exeptions.DaoException;

import java.util.Optional;

public interface PromotionDao extends Dao<Promotion> {

    /**
     * Method to get promotion by name from data base
     *
     * @param name name of promotion
     * @return optional of Promotion
     * @throws DaoException
     */
    Optional<Promotion> findByName(String name) throws DaoException;
}
