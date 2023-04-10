package lt.vu.mif.api.usecases;

import lt.vu.mif.api.entities.Order;
import lt.vu.mif.api.entities.OrderItem;
import lt.vu.mif.api.entities.Product;
import lt.vu.mif.api.persistence.OrderItemsDao;
import lt.vu.mif.api.persistence.OrdersDao;
import lt.vu.mif.api.persistence.ProductsDao;
import lt.vu.mif.api.contracts.OrderCreateDto;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CreateOrder {
    @Inject
    private ProductsDao productsDao;

    @Inject
    private OrdersDao ordersDao;

    @Inject
    private OrderItemsDao orderItemsDao;

    @Transactional
    public int createNewOrder(OrderCreateDto dto) {
        Order order = new Order();
        order.setDateCreated(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderCreateDto.OrderItemDto item : dto.getItems()) {
            Product product = productsDao.find(item.getProductId());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            orderItem.setTotal(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

            orderItems.add(orderItem);
        }

        order.setSubtotal(orderItems.stream().map(OrderItem::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add));
        order.setDiscountTotal(orderItems.stream().map(OrderItem::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add));
        order.setTotal(order.getSubtotal().subtract(order.getDiscountTotal()));

        ordersDao.persist(order);
        orderItems.forEach(orderItemsDao::persist);

        return order.getId();
    }
}
