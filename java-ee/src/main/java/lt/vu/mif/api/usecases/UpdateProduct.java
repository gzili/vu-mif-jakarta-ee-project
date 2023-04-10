package lt.vu.mif.api.usecases;

import lt.vu.mif.api.entities.Product;
import lt.vu.mif.api.persistence.ProductsRepository;
import lt.vu.mif.api.contracts.ProductUpdateDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UpdateProduct {
    @Inject
    private ProductsRepository productsRepository;

    @Transactional
    public void handle(int productId, ProductUpdateDto dto) {
        Product existingProduct = productsRepository.find(productId);

        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName(dto.getName());
        updatedProduct.setPrice(dto.getPrice());
        updatedProduct.setCategories(existingProduct.getCategories());
        updatedProduct.setVersion(dto.getVersion());

        productsRepository.update(updatedProduct);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void forceUpdate(int productId, ProductUpdateDto dto) {
        Product product = productsRepository.find(productId);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        productsRepository.update(product);
    }
}
