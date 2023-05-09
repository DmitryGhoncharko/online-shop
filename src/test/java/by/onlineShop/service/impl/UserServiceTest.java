package by.onlineShop.service.impl;

import by.onlineShop.dao.connection.ConnectionPool;
import by.onlineShop.entity.User;
import by.onlineShop.exeptions.ConnectionException;
import by.onlineShop.exeptions.ServiceException;
import by.onlineShop.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @BeforeAll
    static void initializeConnectionPool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }

    @Test
    public void testLogin_ShouldReturnUser_WhenDataIsCorrect() throws ServiceException {
        Optional<User> actual = userService.login("17ekaterina17@mail.ru", "000Kate");
        assertTrue(actual.isPresent());
    }

    @Test
    public void testLogin_ShouldReturnNull_WhenDataIsNotCorrect() throws ServiceException {
        Optional<User> actual = userService.login("17ekaterina17@mail.ru", "111Kate");
        assertFalse(actual.isPresent());
    }

    @Test
    public void testLogin_ShouldReturnNull_WhenDataIsNull() throws ServiceException {
        Optional<User> actual = userService.login(null, null);
        assertFalse(actual.isPresent());
    }
}