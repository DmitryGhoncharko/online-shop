package by.onlineShop.dao.impl;

import by.onlineShop.dao.AbstractDao;
import by.onlineShop.dao.Table;
import by.onlineShop.dao.UserOrderDao;
import by.onlineShop.dao.mapper.RowMapperFactory;
import by.onlineShop.entity.UserOrder;
import by.onlineShop.exeptions.DaoException;

import java.util.List;

public class UserOrderDaoImpl extends AbstractDao<UserOrder> implements UserOrderDao {
    private static final String FIND_USER_ORDERS_BY_STATUS_QUERY = "SELECT * FROM " + Table.USER_ORDER + " WHERE status=?";
    private static final String UPDATE_USER_ORDER_STATUS_BY_ID_QUERY = "UPDATE " + Table.USER_ORDER + " SET status=? WHERE id=?";
    private static final String SAVE_USER_ORDER_QUERY = "INSERT INTO " + Table.USER_ORDER + " (address, order_date, delivery_date, status) VALUES (?, ?, ?, ?)";

    public UserOrderDaoImpl() {
        super(RowMapperFactory.getInstance().getUserOrderRowMapper(), Table.USER_ORDER);
    }

    @Override
    public List<UserOrder> findByStatus(String status) throws DaoException {
        return executeQuery(FIND_USER_ORDERS_BY_STATUS_QUERY, status);
    }

    @Override
    public void updateStatusById(long id, String status) throws DaoException {
        executeUpdateQuery(UPDATE_USER_ORDER_STATUS_BY_ID_QUERY, status, id);
    }

    @Override
    public long save(UserOrder userOrder) throws DaoException {
        return executeInsertQuery(SAVE_USER_ORDER_QUERY, userOrder.getAddress(), userOrder.getOrderDate(),
                userOrder.getDeliveryDate(), userOrder.getStatus());
    }
    @Override
    public void deleteByUserId(Long userId) throws DaoException{
        executeUpdateQuery("delete from Orders where user_id = ? ", userId);
    }
}
