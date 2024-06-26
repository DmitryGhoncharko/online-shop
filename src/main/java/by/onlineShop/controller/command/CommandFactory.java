package by.onlineShop.controller.command;

import by.onlineShop.controller.command.impl.*;
import by.onlineShop.controller.command.impl.transition.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {
    private static final Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put(CommandName.MAIN_COMMAND, new GoToMainCommand());
        commands.put(CommandName.PROFILE_COMMAND, new GoToProfileCommand());
        commands.put(CommandName.REGISTRATION_COMMAND, new LogUpCommand());
        commands.put(CommandName.LOG_IN_COMMAND, new GoToLogInCommand());
        commands.put(CommandName.CONTACTS_COMMAND, new GoToContactsCommand());
        commands.put(CommandName.PROMOTIONS_COMMAND, new GoToPromotionsCommand());
        commands.put(CommandName.CATALOG_COMMAND, new GoToCatalogCommand());
        commands.put(CommandName.CHECK_LOGIN_COMMAND, new LogInCommand());
        commands.put(CommandName.LOG_OUT_COMMAND, new LogOutCommand());
        commands.put(CommandName.LOG_UP_COMMAND, new GoToLogUpCommand());
        commands.put(CommandName.MY_ORDERS_COMMAND, new GoToMyOrdersCommand());
        commands.put(CommandName.BASKET_COMMAND, new GoToBasketCommand());
        commands.put(CommandName.VIEW_ORDERS_COMMAND, new GoToViewOrdersCommand());
        commands.put(CommandName.ADD_PRODUCT_COMMAND, new GoToAddProductCommand());
        commands.put(CommandName.ADD_PROMOTION_COMMAND, new GoToAddPromotionCommand());
        commands.put(CommandName.ADD_ORDER_COMMAND, new GoToAddOrderCommand());
        commands.put(CommandName.DELETE_ORDER_COMMAND, new DeleteOrderCommand());
        commands.put(CommandName.COMPLETE_USER_ORDER_COMMAND, new CompleteOrderCommand());
        commands.put(CommandName.ADD_TO_BASKET_COMMAND, new AddToBasketCommand());
        commands.put(CommandName.CONFIRM_ORDER_COMMAND, new ConfirmOrderCommand());
        commands.put(CommandName.CONFIRM_ADDING_PRODUCT_COMMAND, new ConfirmAddingProductCommand());
        commands.put(CommandName.DELETE_USER_ORDER_COMMAND, new DeleteUserOrderCommand());
        commands.put(CommandName.EDIT_PRODUCT_COMMAND, new GoToEditProductCommand());
        commands.put(CommandName.CONFIRM_EDIT_PRODUCT, new ConfirmProductChangeCommand());
        commands.put(CommandName.CONFIRM_ADDING_PROMOTION_COMMAND, new ConfirmAddingPromotionCommand());
        commands.put(CommandName.GOOD,new GoodCommand());
        commands.put(CommandName.AB,new AB());
    }

    public static CommandFactory getInstance() {
        return Holder.INSTANCE;
    }

    public Command getCommand(String name) {
        return Optional.ofNullable(commands.get(name)).orElse(commands.get(CommandName.DEFAULT_COMMAND));
    }

    private static class Holder {
        static final CommandFactory INSTANCE = new CommandFactory();
    }

}
