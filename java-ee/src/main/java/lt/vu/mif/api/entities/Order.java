package lt.vu.mif.api.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    private BigDecimal subtotal;

    @Column(name = "discount_total")
    private BigDecimal discountTotal;

    private BigDecimal total;
}
