package by.onlineShop.controller.command.impl.transition;

import by.onlineShop.controller.command.Command;
import by.onlineShop.controller.command.CommandResult;
import by.onlineShop.controller.command.CommandResultType;
import by.onlineShop.controller.context.RequestContext;
import by.onlineShop.controller.context.RequestContextHelper;
import by.onlineShop.entity.Order;
import by.onlineShop.entity.User;
import by.onlineShop.exeptions.ServiceException;
import by.onlineShop.service.OrderService;
import by.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToAddOrderCommand implements Command {
    private static final String PAGE = "WEB-INF/view/addOrder.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String USER = "user";
    private static final String TOTAL_COST = "totalCost";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        long userId = user.getId();

        try {
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            List<Order> orders = orderService.retrieveOrdersByUserWhereProductStatusTrue(userId);

            double totalPrice = orderService.calculateTotalCost(orders);
            requestContext.addRequestAttribute(TOTAL_COST, totalPrice);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}