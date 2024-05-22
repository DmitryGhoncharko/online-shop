package by.onlineShop.controller.command.impl;

import by.onlineShop.controller.command.Command;
import by.onlineShop.controller.command.CommandResult;
import by.onlineShop.controller.command.CommandResultType;
import by.onlineShop.controller.context.RequestContext;
import by.onlineShop.controller.context.RequestContextHelper;
import by.onlineShop.dao.DaoFactory;
import by.onlineShop.dao.UserOrderDao;
import by.onlineShop.entity.Order;
import by.onlineShop.entity.User;
import by.onlineShop.exeptions.DaoException;
import by.onlineShop.exeptions.ServiceException;
import by.onlineShop.service.OrderService;
import by.onlineShop.service.ServiceFactory;
import by.onlineShop.service.UserOrderService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ConfirmOrderCommand implements Command {
    private static final String ADD_ORDER_PAGE = "WEB-INF/view/addOrder.jsp";
    private static final String MY_ORDERS_PAGE = "command=good";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ADDRESS = "address";
    private static final String DELIVERY_DATE = "delivery-date";
    private static final String CARDHOLDER_NAME = "cardholder-name";
    private static final String CVV = "cvv";
    private static final String CARD_NUMBER = "card-number";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private static final String USER = "user";
    private static final String TOTAL_COST = "totalCost";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        User user = (User) requestContext.getSessionAttribute(USER);
        UserOrderDao userOrderDao = DaoFactory.getInstance().getUserOrderDao();
        try {
            userOrderDao.deleteByUserId(user.getId());
        } catch (DaoException e) {
            return new CommandResult(MY_ORDERS_PAGE, CommandResultType.REDIRECT);

        }
        return new CommandResult(MY_ORDERS_PAGE, CommandResultType.REDIRECT);


    }
    }



