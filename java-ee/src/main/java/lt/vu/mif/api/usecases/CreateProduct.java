package lt.vu.mif.api.usecases;

import lt.vu.mif.api.contracts.ProductCreateDto;

public interface CreateProduct {
    int handle(ProductCreateDto dto);
}
