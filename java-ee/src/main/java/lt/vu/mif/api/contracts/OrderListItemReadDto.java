package lt.vu.mif.api.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.api.entities.Order;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class OrderListItemReadDto {
    private int id;

    private String dateCreated;

    private BigDecimal subtotal;

    private BigDecimal discountTotal;

    private BigDecimal total;

    public static OrderListItemReadDto fromOrder(Order order) {
        OrderListItemReadDto dto = new OrderListItemReadDto();
        dto.setId(order.getId());
        dto.setDateCreated(order.getDateCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        dto.setSubtotal(order.getSubtotal());
        dto.setDiscountTotal(order.getDiscountTotal());
        dto.setTotal(order.getTotal());
        return dto;
    }
}
