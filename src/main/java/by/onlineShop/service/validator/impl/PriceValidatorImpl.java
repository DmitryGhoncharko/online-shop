package by.onlineShop.service.validator.impl;

import by.onlineShop.service.validator.AbstractValidator;

public class PriceValidatorImpl extends AbstractValidator {
    private static final String PRICE_REGEX = "^\\d+\\.\\d{0,2}$";

    @Override
    protected String getRegex() {
        return PRICE_REGEX;
    }
}
