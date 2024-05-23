package by.onlineShop.controller.command.impl.transition;

import by.onlineShop.controller.command.Command;
import by.onlineShop.controller.command.CommandResult;
import by.onlineShop.controller.command.CommandResultType;
import by.onlineShop.controller.context.RequestContextHelper;

import javax.servlet.http.HttpServletResponse;

public class AB implements Command {
    private static final String PAGE = "WEB-INF/view/ab.jsp";
    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
