package by.onlineShop.controller.command.impl;

import by.onlineShop.controller.command.Command;
import by.onlineShop.controller.command.CommandResult;
import by.onlineShop.controller.command.CommandResultType;
import by.onlineShop.controller.context.RequestContext;
import by.onlineShop.controller.context.RequestContextHelper;
import by.onlineShop.entity.Product;
import by.onlineShop.entity.User;
import by.onlineShop.exeptions.ServiceException;
import by.onlineShop.service.OrderService;
import by.onlineShop.service.ProductService;
import by.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddToBasketCommand implements Command {
    private static final String PAGE = "command=catalog";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORY_ID_PARAMETER = "&categoryId=";
    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String USER = "user";
    private static final String PRODUCT_ID = "productId";
    private static final String QUANTITY = "quantity";
    private static final String OK = "ok";
    private static final String ERROR = "error";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        long userId = user.getId();
        try {
            long productId = Long.parseLong(requestContext.getRequestParameter(PRODUCT_ID));
            int quantity = Integer.parseInt(requestContext.getRequestParameter(QUANTITY));
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            String message = OK;
            boolean result = orderService.addNewOrder(userId, productId, quantity);
            if (!result) message = ERROR;

            ProductService productService = ServiceFactory.getInstance().getProductService();
            Optional<Product> product = productService.retrieveProductById(productId);

            long categoryId = 0;
            if (product.isPresent()) {
                categoryId = product.get().getCategoryId();
            }

            helper.updateRequest(requestContext);
            return new CommandResult(PAGE + CATEGORY_ID_PARAMETER + categoryId
                    + MESSAGE_PARAMETER + message, CommandResultType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
    }
}
