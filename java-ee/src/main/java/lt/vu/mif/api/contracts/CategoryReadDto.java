package lt.vu.mif.api.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.api.entities.Category;

@Getter
@Setter
public class CategoryReadDto {
    private int id;

    private String name;

    public static CategoryReadDto FromCategory(Category category) {
        CategoryReadDto dto = new CategoryReadDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
