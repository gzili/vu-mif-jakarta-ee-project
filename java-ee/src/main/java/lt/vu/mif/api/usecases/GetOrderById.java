package lt.vu.mif.api.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lt.vu.mif.api.contracts.OrderReadDto;
import lt.vu.mif.api.entities.Order;
import lt.vu.mif.api.persistence.OrdersDao;

@ApplicationScoped
public class GetOrderById {
    @Inject
    private OrdersDao ordersDao;

    public OrderReadDto handle(int orderId) {
        Order order = ordersDao.find(orderId);
        return OrderReadDto.fromOrder(order);
    }
}
