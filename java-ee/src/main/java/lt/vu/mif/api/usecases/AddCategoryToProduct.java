package lt.vu.mif.api.usecases;

import lt.vu.mif.api.contracts.ProductAddRemoveCategoryDto;
import lt.vu.mif.api.contracts.ProductReadDto;

public interface AddCategoryToProduct {
    ProductReadDto handle(int productId, ProductAddRemoveCategoryDto dto);
}
