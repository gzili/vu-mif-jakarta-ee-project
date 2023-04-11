package lt.vu.mif.api.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lt.vu.mif.api.contracts.ProductAddRemoveCategoryDto;
import lt.vu.mif.api.contracts.ProductReadDto;
import lt.vu.mif.api.entities.Category;
import lt.vu.mif.api.entities.Product;
import lt.vu.mif.api.persistence.CategoriesDao;
import lt.vu.mif.api.persistence.ProductsDao;

@ApplicationScoped
public class AddCategoryToProductImpl implements AddCategoryToProduct {
    @Inject
    private ProductsDao productsDao;

    @Inject
    private CategoriesDao categoriesDao;

    public ProductReadDto handle(int productId, ProductAddRemoveCategoryDto dto) {
        Product product = productsDao.find(productId);
        Category category = categoriesDao.find(dto.getCategoryId());
        product.getCategories().add(category);
        return ProductReadDto.FromProduct(product);
    }
}
