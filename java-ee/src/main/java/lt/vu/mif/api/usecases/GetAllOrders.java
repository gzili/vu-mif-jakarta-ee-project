package lt.vu.mif.api.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lt.vu.mif.api.contracts.OrderListItemReadDto;
import lt.vu.mif.api.persistence.OrdersDao;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GetAllOrders {
    @Inject
    private OrdersDao ordersDao;

    public List<OrderListItemReadDto> handle() {
        return ordersDao
                .findAll()
                .stream()
                .map(OrderListItemReadDto::fromOrder)
                .collect(Collectors.toList());
    }
}
