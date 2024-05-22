package by.onlineShop.dao;

import by.onlineShop.entity.UserOrder;
import by.onlineShop.exeptions.DaoException;

import java.util.List;

public interface UserOrderDao extends Dao<UserOrder> {

    /**
     * Method to get user orders by status from data base
     *
     * @param status user order status
     * @return List of user orders
     * @throws DaoException
     */
    List<UserOrder> findByStatus(String status) throws DaoException;

    /**
     * Method to update status in user order by ID in data base
     *
     * @param id     ID of user order to update
     * @param status new user order status
     * @throws DaoException
     */
    void updateStatusById(long id, String status) throws DaoException;

    public void deleteByUserId(Long userId) throws DaoException;

}
