package lt.vu.mif.api.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.api.entities.Category;
import lt.vu.mif.api.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductReadDto {
    private int id;

    private String name;

    private BigDecimal price;

    private int version;

    private List<ProductCategoryReadDto> categories;

    public static ProductReadDto FromProduct(Product product) {
        ProductReadDto dto = new ProductReadDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setVersion(product.getVersion());

        List<Category> categories = product.getCategories();
        if (categories != null) {
            dto.setCategories(categories.stream().map(ProductCategoryReadDto::FromCategory).collect(Collectors.toList()));
        }

        return dto;
    }
}
