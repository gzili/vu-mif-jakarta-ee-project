package lt.vu.mif.api.usecases;

import lt.vu.mif.api.entities.Product;
import lt.vu.mif.api.contracts.ProductCreateDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lt.vu.mif.api.persistence.mybatis.ProductMapper;

@ApplicationScoped
public class CreateProduct {
    @Inject
    ProductMapper productMapper;

    @Transactional
    public int handle(ProductCreateDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        productMapper.insert(product);

        return product.getId();
    }
}