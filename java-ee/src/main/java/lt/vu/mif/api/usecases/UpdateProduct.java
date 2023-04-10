package lt.vu.mif.api.usecases;

import lt.vu.mif.api.entities.Product;
import lt.vu.mif.api.persistence.ProductsDao;
import lt.vu.mif.api.contracts.ProductUpdateDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UpdateProduct {
    @Inject
    private ProductsDao productsDao;

    @Transactional
    public void handle(int productId, ProductUpdateDto dto) {
        Product existingProduct = productsDao.find(productId);

        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName(dto.getName());
        updatedProduct.setPrice(dto.getPrice());
        updatedProduct.setCategories(existingProduct.getCategories());
        updatedProduct.setVersion(dto.getVersion());

        productsDao.update(updatedProduct);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void forceUpdate(int productId, ProductUpdateDto dto) {
        Product product = productsDao.find(productId);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        productsDao.update(product);
    }
}
