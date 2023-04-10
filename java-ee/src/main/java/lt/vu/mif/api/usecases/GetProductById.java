package lt.vu.mif.api.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lt.vu.mif.api.contracts.ProductReadDto;
import lt.vu.mif.api.entities.Product;
import lt.vu.mif.api.persistence.ProductsDao;

@ApplicationScoped
public class GetProductById {
    @Inject
    private ProductsDao productsDao;

    public ProductReadDto handle(int productId) {
        Product product = productsDao.find(productId);
        return ProductReadDto.FromProduct(product);
    }
}
