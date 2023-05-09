package by.onlineShop.dao.impl;

import by.onlineShop.dao.AbstractDao;
import by.onlineShop.dao.Table;
import by.onlineShop.dao.UserDao;
import by.onlineShop.dao.mapper.RowMapperFactory;
import by.onlineShop.entity.User;
import by.onlineShop.exeptions.DaoException;

import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD_QUERY = "SELECT * FROM " + Table.USER + " WHERE email=? and password=?";
    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM " + Table.USER + " WHERE email=?";
    private static final String SAVE_USER_QUERY = "INSERT INTO " + Table.USER + " (email, password, role_id, userInformation_id) VALUES (?, ?, ?, ?)";
    private static final String DELETE_USER_QUERY = "DELETE FROM " + Table.USER + " WHERE id=?";

    public UserDaoImpl() {
        super(RowMapperFactory.getInstance().getUserRowMapper(), Table.USER);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        return executeQueryForSingleResult(FIND_USER_BY_EMAIL_AND_PASSWORD_QUERY, email, password);
    }

    @Override
    public long save(User user) throws DaoException {
        return executeInsertQuery(SAVE_USER_QUERY, user.getEmail(), user.getPassword(),
                user.getRoleId(), user.getUserInformationId());
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        return executeQueryForSingleResult(FIND_USER_BY_EMAIL_QUERY, email);
    }

    @Override
    public void removeById(long id) throws DaoException {
        executeUpdateQuery(DELETE_USER_QUERY, id);
    }
}
