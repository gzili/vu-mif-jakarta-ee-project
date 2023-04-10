package lt.vu.mif.api.controllers;

import lt.vu.mif.api.entities.Order;
import lt.vu.mif.api.persistence.OrdersDao;
import lt.vu.mif.api.contracts.OrderCreateDto;
import lt.vu.mif.api.contracts.OrderListItemReadDto;
import lt.vu.mif.api.contracts.OrderReadDto;
import lt.vu.mif.api.usecases.CreateOrder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/orders")
public class OrdersController {
    @Inject
    private OrdersDao ordersDao;

    @Inject
    private CreateOrder createOrder;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(OrderCreateDto dto) {
        int id = createOrder.createNewOrder(dto);
        return Response.ok(id).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<OrderListItemReadDto> orders = ordersDao
                .findAll()
                .stream()
                .map(OrderListItemReadDto::fromOrder)
                .collect(Collectors.toList());
        return Response.ok(orders).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final int id) {
        Order order = ordersDao.find(id);
        OrderReadDto dto = OrderReadDto.fromOrder(order);
        return Response.ok(dto).build();
    }
}
