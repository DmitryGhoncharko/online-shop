package by.onlineShop.dao;

import by.onlineShop.entity.Role;
import by.onlineShop.exeptions.DaoException;

import java.util.Optional;

public interface RoleDao extends Dao<Role> {

    /**
     * Method to get role by name from data base
     *
     * @param name role name
     * @return optional of Role
     * @throws DaoException
     */
    Optional<Role> findByName(String name) throws DaoException;
}
