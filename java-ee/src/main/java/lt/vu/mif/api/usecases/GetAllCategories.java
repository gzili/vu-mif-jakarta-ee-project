package lt.vu.mif.api.usecases;

import jakarta.inject.Inject;
import lt.vu.mif.api.contracts.CategoryReadDto;
import lt.vu.mif.api.persistence.CategoriesDao;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllCategories {
    @Inject
    private CategoriesDao categoriesDao;

    public List<CategoryReadDto> handle() {
        return categoriesDao
                .findAll()
                .stream()
                .map(CategoryReadDto::FromCategory)
                .collect(Collectors.toList());
    }
}
