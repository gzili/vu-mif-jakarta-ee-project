package lt.vu.mif.api.contracts;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductCreateDto {
    private String name;

    private BigDecimal price;
}
