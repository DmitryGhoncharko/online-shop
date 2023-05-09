package by.onlineShop.service.impl;

import by.onlineShop.dao.DaoFactory;
import by.onlineShop.dao.RoleDao;
import by.onlineShop.entity.Role;
import by.onlineShop.exeptions.DaoException;
import by.onlineShop.exeptions.ServiceException;
import by.onlineShop.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Optional<Role> retrieveRoleById(long roleId) throws ServiceException {
        try {
            RoleDao roleDao = DaoFactory.getInstance().getRoleDao();
            Optional<Role> result;
            result = roleDao.findById(roleId);
            return result;
        } catch (DaoException e) {
            logger.error("Unable to retrieve role by id!");
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
