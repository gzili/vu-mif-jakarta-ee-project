package lt.vu.mif.api.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.api.entities.Category;

@Getter
@Setter
public class ProductCategoryReadDto {
    private int id;

    private String name;

    public static ProductCategoryReadDto FromCategory(Category category) {
        ProductCategoryReadDto dto = new ProductCategoryReadDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
