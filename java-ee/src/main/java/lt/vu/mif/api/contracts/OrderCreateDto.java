package lt.vu.mif.api.contracts;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreateDto {
    @Getter
    @Setter
    public static class OrderItemDto {
        private int productId;

        private int quantity;
    }

    private List<OrderItemDto> items;
}
