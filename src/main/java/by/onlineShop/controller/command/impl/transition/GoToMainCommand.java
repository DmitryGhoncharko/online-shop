package by.onlineShop.controller.command.impl.transition;

import by.onlineShop.controller.command.Command;
import by.onlineShop.controller.command.CommandResult;
import by.onlineShop.controller.command.CommandResultType;
import by.onlineShop.controller.context.RequestContext;
import by.onlineShop.controller.context.RequestContextHelper;
import by.onlineShop.entity.Category;
import by.onlineShop.exeptions.ServiceException;
import by.onlineShop.service.CategoryService;
import by.onlineShop.service.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToMainCommand implements Command {
    private static final String PAGE = "WEB-INF/view/main.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORIES = "categories";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try {
            CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
            List<Category> categories = categoryService.retrieveCategories();
            requestContext.addRequestAttribute(CATEGORIES, categories);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
