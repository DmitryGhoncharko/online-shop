package by.onlineShop.dao.mapper.impl;

import by.onlineShop.dao.mapper.Column;
import by.onlineShop.dao.mapper.RowMapper;
import by.onlineShop.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong(Column.ID));
        order.setUserId(resultSet.getLong(Column.USER_ID));
        order.setUserOrderId(resultSet.getLong(Column.USER_ORDER_ID));
        order.setProductId(resultSet.getLong(Column.PRODUCT_ID));
        order.setNumber(resultSet.getInt(Column.ORDER_NUMBER));
        return order;
    }
}
