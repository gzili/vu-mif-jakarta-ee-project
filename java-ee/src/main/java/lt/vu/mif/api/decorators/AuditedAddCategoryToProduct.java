package lt.vu.mif.api.decorators;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lt.vu.mif.api.contracts.ProductAddRemoveCategoryDto;
import lt.vu.mif.api.contracts.ProductReadDto;
import lt.vu.mif.api.entities.AuditEntry;
import lt.vu.mif.api.persistence.AuditDao;
import lt.vu.mif.api.usecases.AddCategoryToProduct;

import java.time.LocalDateTime;

@Decorator
public abstract class AuditedAddCategoryToProduct implements AddCategoryToProduct {
    @Inject @Delegate @Any
    private AddCategoryToProduct addCategoryToProduct;

    @Inject
    private AuditDao auditDao;

    @Override
    @Transactional
    public ProductReadDto handle(int productId, ProductAddRemoveCategoryDto dto) {
        AuditEntry auditEntry = new AuditEntry();
        auditEntry.setTimestamp(LocalDateTime.now());
        auditEntry.setEvent("add_category");
        auditEntry.setDescription("added category " + dto.getCategoryId() + " to product " + productId);

        auditDao.persist(auditEntry);

        return addCategoryToProduct.handle(productId, dto);
    }
}
