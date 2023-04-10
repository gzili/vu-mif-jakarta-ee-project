package lt.vu.mif.api.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private BigDecimal price;

    private int quantity;

    private BigDecimal subtotal;

    private BigDecimal discount = BigDecimal.ZERO;

    private BigDecimal total;
}
