package lt.vu.mif.api.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lt.vu.mif.api.contracts.ProductAddRemoveCategoryDto;
import lt.vu.mif.api.contracts.ProductCreateDto;
import lt.vu.mif.api.contracts.ProductReadDto;
import lt.vu.mif.api.contracts.ProductUpdateDto;
import lt.vu.mif.api.usecases.*;

import java.util.List;

@ApplicationScoped
@Path("/products")
public class ProductsController {
    @Inject
    private CreateProduct createProduct;

    @Inject
    private UpdateProduct updateProduct;

    @Inject
    private GetAllProducts getAllProducts;

    @Inject
    private GetProductById getProductById;

    @Inject
    private AddCategoryToProductImpl addCategoryToProduct;

    @Inject
    private RemoveCategoryFromProduct removeCategoryFromProduct;

    @POST
    public Response create(ProductCreateDto dto) {
        int id = createProduct.handle(dto);
        return Response.ok(id).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("categoryId") final int categoryId) {
        List<ProductReadDto> products = getAllProducts.handle(categoryId);
        return Response.ok(products).build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final int id) {
        ProductReadDto product = getProductById.handle(id);
        return Response.ok(product).build();
    }

    @Path("{id}")
    @PUT
    public Response update(@PathParam("id") final int id, ProductUpdateDto dto, @QueryParam("force") final boolean force) {
        try {
            updateProduct.handle(id, dto);
        } catch (OptimisticLockException e) {
            if (force) {
                updateProduct.forceUpdate(id, dto);
            } else {
                return Response.status(409).build();
            }
        }
        return Response.noContent().build();
    }

    @Path("{id}/categories")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addCategory(@PathParam("id") final int id, ProductAddRemoveCategoryDto dto) {
        ProductReadDto updatedProduct = addCategoryToProduct.handle(id, dto);
        return Response.ok(updatedProduct).build();
    }

    @Path("{id}/categories")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeCategory(@PathParam("id") final int id, ProductAddRemoveCategoryDto dto) {
        ProductReadDto updatedProduct = removeCategoryFromProduct.handle(id, dto);
        return Response.ok(updatedProduct).build();
    }
}
