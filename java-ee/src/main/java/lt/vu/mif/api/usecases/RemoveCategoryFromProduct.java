package lt.vu.mif.api.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lt.vu.mif.api.contracts.ProductAddRemoveCategoryDto;
import lt.vu.mif.api.contracts.ProductReadDto;
import lt.vu.mif.api.entities.Category;
import lt.vu.mif.api.entities.Product;
import lt.vu.mif.api.persistence.ProductsDao;

@ApplicationScoped
public class RemoveCategoryFromProduct {
    @Inject
    private ProductsDao productsDao;

    public ProductReadDto handle(int productId, ProductAddRemoveCategoryDto dto) {
        Product product = productsDao.find(productId);
        Category category = product.getCategories().stream().filter(c -> c.getId() == dto.getCategoryId()).findFirst().get();
        product.getCategories().remove(category);
        return ProductReadDto.FromProduct(product);
    }
}
