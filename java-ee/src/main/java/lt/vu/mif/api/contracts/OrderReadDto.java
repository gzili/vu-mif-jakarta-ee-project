package lt.vu.mif.api.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.api.entities.Order;
import lt.vu.mif.api.entities.OrderItem;
import lt.vu.mif.api.entities.Product;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderReadDto {
    @Getter
    @Setter
    public static class OrderItemDto {
        @Getter
        @Setter
        public static class ProductDto {
            private int id;

            private String name;

            public static ProductDto fromProduct(Product product) {
                ProductDto dto = new ProductDto();
                dto.setId(product.getId());
                dto.setName(product.getName());
                return  dto;
            }
        }

        private int id;

        private ProductDto product;

        private BigDecimal price;

        private int quantity;

        private BigDecimal subtotal;

        private BigDecimal discount;

        private BigDecimal total;

        public static OrderItemDto fromOrderItem(OrderItem orderItem) {
            OrderItemDto dto = new OrderItemDto();
            dto.setId(orderItem.getId());
            dto.setProduct(ProductDto.fromProduct(orderItem.getProduct()));
            dto.setPrice(orderItem.getPrice());
            dto.setQuantity(orderItem.getQuantity());
            dto.setSubtotal(orderItem.getSubtotal());
            dto.setDiscount(orderItem.getDiscount());
            dto.setTotal(orderItem.getTotal());
            return dto;
        }
    }

    private int id;

    private String dateCreated;

    private List<OrderItemDto> items;

    private BigDecimal subtotal;

    private BigDecimal discountTotal;

    private BigDecimal total;

    public static OrderReadDto fromOrder(Order order) {
        OrderReadDto dto = new OrderReadDto();
        dto.setId(order.getId());
        dto.setDateCreated(order.getDateCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        dto.items = order.getItems().stream().map(OrderItemDto::fromOrderItem).collect(Collectors.toList());
        dto.setSubtotal(order.getSubtotal());
        dto.setDiscountTotal(order.getDiscountTotal());
        dto.setTotal(order.getTotal());
        return dto;
    }
}
