package lt.vu.mif.api.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Specializes;
import lt.vu.mif.api.entities.OrderItem;
import lt.vu.mif.api.interceptors.Logged;

import java.math.BigDecimal;

@ApplicationScoped
@Specializes
public class GlobalDiscountCalculator extends DiscountCalculator {
    @Logged
    @Override
    public void setOrderItemDiscount(OrderItem orderItem) {
        super.setOrderItemDiscount(orderItem);
        orderItem.setDiscount(orderItem.getDiscount().add(orderItem.getSubtotal().multiply(new BigDecimal("0.1"))));
    }
}
