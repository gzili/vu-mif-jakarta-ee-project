package lt.vu.mif.api.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lt.vu.mif.api.contracts.OrderCreateDto;
import lt.vu.mif.api.contracts.OrderListItemReadDto;
import lt.vu.mif.api.contracts.OrderReadDto;
import lt.vu.mif.api.usecases.CreateOrder;
import lt.vu.mif.api.usecases.GetAllOrders;
import lt.vu.mif.api.usecases.GetOrderById;

import java.util.List;

@ApplicationScoped
@Path("/orders")
public class OrdersController {
    @Inject
    private CreateOrder createOrder;

    @Inject
    private GetAllOrders getAllOrders;

    @Inject
    private GetOrderById getOrderById;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(OrderCreateDto dto) {
        int id = createOrder.createNewOrder(dto);
        return Response.ok(id).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<OrderListItemReadDto> orders = getAllOrders.handle();
        return Response.ok(orders).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final int id) {
        OrderReadDto order = getOrderById.handle(id);
        return Response.ok(order).build();
    }
}
