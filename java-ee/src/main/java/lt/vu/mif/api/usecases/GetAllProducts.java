package lt.vu.mif.api.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lt.vu.mif.api.contracts.ProductReadDto;
import lt.vu.mif.api.entities.Product;
import lt.vu.mif.api.persistence.ProductsDao;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GetAllProducts {
    @Inject
    private ProductsDao productsDao;

    public List<ProductReadDto> handle(int categoryId) {
        List<Product> products;
        if (categoryId > 0) {
            products = productsDao.findByCategory(categoryId);
        } else {
            products = productsDao.findAll();
        }

        return products
                .stream()
                .map(ProductReadDto::FromProduct)
                .collect(Collectors.toList());
    }
}
