package by.onlineShop.controller.command.impl;

import by.onlineShop.controller.command.Command;
import by.onlineShop.controller.command.CommandResult;
import by.onlineShop.controller.command.CommandResultType;
import by.onlineShop.controller.context.RequestContext;
import by.onlineShop.controller.context.RequestContextHelper;
import by.onlineShop.exeptions.ServiceException;
import by.onlineShop.service.ServiceFactory;
import by.onlineShop.service.UserOrderService;

import javax.servlet.http.HttpServletResponse;

public class CompleteOrderCommand implements Command {
    private static final String PAGE = "command=viewOrders";
    private static final String USER_ORDER_ID = "userOrderId";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try {
            long userOrderId = Long.parseLong(requestContext.getRequestParameter(USER_ORDER_ID));
            UserOrderService userOrderService = ServiceFactory.getInstance().getUserOrderService();
            userOrderService.updateStatusAtUserOrderById(userOrderId, "получен");
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.REDIRECT);
    }
}
