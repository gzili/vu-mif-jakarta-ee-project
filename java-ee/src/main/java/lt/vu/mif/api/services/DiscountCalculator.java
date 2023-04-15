package lt.vu.mif.api.services;

import jakarta.enterprise.context.ApplicationScoped;
import lt.vu.mif.api.entities.OrderItem;
import lt.vu.mif.api.interceptors.Logged;

import java.math.BigDecimal;

@ApplicationScoped
public class DiscountCalculator {
    @Logged
    public void setOrderItemDiscount(OrderItem orderItem) {
        if (orderItem.getProduct().getName().toLowerCase().contains("behringer")) {
            orderItem.setDiscount(orderItem.getSubtotal().multiply(new BigDecimal("0.1")));
        }
    }
}
