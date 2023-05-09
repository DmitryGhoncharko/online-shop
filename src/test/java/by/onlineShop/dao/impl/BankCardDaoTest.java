package by.onlineShop.dao.impl;

import by.onlineShop.dao.BankCardDao;
import by.onlineShop.dao.connection.ConnectionPool;
import by.onlineShop.entity.BankCard;
import by.onlineShop.exeptions.ConnectionException;
import by.onlineShop.exeptions.DaoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankCardDaoTest {
    BankCardDao bankCardDao = new BankCardDaoImpl();

    @BeforeAll
    static void initializeConnectionPool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }

    @Test
    public void testFindByCardNumber_ShouldReturnBankCard_WhenDataIsCorrect() throws DaoException {
        Optional<BankCard> actual = bankCardDao.findByCardNumber(4276880099433504L);
        BankCard expected = new BankCard();
        expected.setId(2);
        expected.setCardNumber(4276880099433504L);
        expected.setExpirationYear(2022);
        expected.setExpirationMonth(11);
        expected.setCardholderName("VICTOR PETROV");
        expected.setCvv(291);
        expected.setBalance(512.32);
        assertEquals(expected, actual.get());
    }
}