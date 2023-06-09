package by.onlineShop.service;

import by.onlineShop.entity.Category;
import by.onlineShop.exeptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    /**
     * Method to retrieve categories
     *
     * @return List of categories
     * @throws ServiceException
     */
    List<Category> retrieveCategories() throws ServiceException;

    /**
     * Method to retrieve category by ID
     *
     * @param categoryId ID of category to retrieve
     * @return optional of Category
     * @throws ServiceException
     */
    Optional<Category> retrieveCategoryBtId(long categoryId) throws ServiceException;
}
