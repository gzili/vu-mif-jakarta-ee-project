package lt.vu.mif.api.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lt.vu.mif.api.contracts.ProductCreateDto;
import lt.vu.mif.api.entities.Product;
import lt.vu.mif.api.persistence.ProductsDao;

@Alternative
@ApplicationScoped
public class CreateProductJpa implements CreateProduct {
    @Inject
    private ProductsDao productsDao;

    @Transactional
    public int handle(ProductCreateDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        productsDao.persist(product);

        return product.getId();
    }
}
