package lt.vu.mif.api.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lt.vu.mif.api.entities.Product;
import lt.vu.mif.api.persistence.ProductsRepository;

@ApplicationScoped
@Path("/products")
public class ProductsController {
    @Inject
    private ProductsRepository productsRepository;

    @POST
    @Transactional
    public Response create(Product product) {
        productsRepository.persist(product);
        return Response.ok(product.getId()).build();
    }
}
