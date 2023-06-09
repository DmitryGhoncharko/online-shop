package by.onlineShop.controller.command.impl;

import by.onlineShop.controller.command.Command;
import by.onlineShop.controller.command.CommandResult;
import by.onlineShop.controller.command.CommandResultType;
import by.onlineShop.controller.context.RequestContext;
import by.onlineShop.controller.context.RequestContextHelper;
import by.onlineShop.exeptions.ServiceException;
import by.onlineShop.service.OrderService;
import by.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;

public class DeleteOrderCommand implements Command {
    private static final String PAGE = "command=basket";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String ORDER_ID = "orderId";


    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try {
            long orderId = Long.parseLong(requestContext.getRequestParameter(ORDER_ID));
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            orderService.removeOrderById(orderId);
        } catch (ServiceException | NumberFormatException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.REDIRECT);
    }
}
