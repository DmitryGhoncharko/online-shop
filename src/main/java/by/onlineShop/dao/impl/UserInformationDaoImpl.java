package by.onlineShop.dao.impl;

import by.onlineShop.dao.AbstractDao;
import by.onlineShop.dao.Table;
import by.onlineShop.dao.mapper.RowMapperFactory;
import by.onlineShop.entity.UserInformation;
import by.onlineShop.exeptions.DaoException;

public class UserInformationDaoImpl extends AbstractDao<UserInformation> {
    private static final String SAVE_USER_INFORMATION_QUERY = "INSERT INTO " + Table.USER_INFORMATION +
            " (name, surname, patronymic, phone) VALUES (?, ?, ?, ?)";

    public UserInformationDaoImpl() {
        super(RowMapperFactory.getInstance().getUserInformationRowMapper(), Table.USER_INFORMATION);
    }

    @Override
    public long save(UserInformation userInformation) throws DaoException {
        return executeInsertQuery(SAVE_USER_INFORMATION_QUERY, userInformation.getName(),
                userInformation.getSurname(), userInformation.getPatronymic(), userInformation.getPhone());
    }
}
