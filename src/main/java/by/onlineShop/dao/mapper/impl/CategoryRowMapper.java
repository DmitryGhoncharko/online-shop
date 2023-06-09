package by.onlineShop.dao.mapper.impl;

import by.onlineShop.dao.mapper.Column;
import by.onlineShop.dao.mapper.RowMapper;
import by.onlineShop.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category map(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong(Column.ID));
        category.setCategoryName(resultSet.getString(Column.CATEGORY_NAME));
        return category;
    }
}
