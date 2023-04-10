package lt.vu.mif.api.controllers;

import lt.vu.mif.api.entities.Category;
import lt.vu.mif.api.entities.Product;
import lt.vu.mif.api.persistence.CategoriesDao;
import lt.vu.mif.api.persistence.ProductsDao;
import lt.vu.mif.api.contracts.*;
import lt.vu.mif.api.usecases.CreateProduct;
import lt.vu.mif.api.usecases.UpdateProduct;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/products")
public class ProductsController {
    @Inject
    private ProductsDao productsDao;

    @Inject
    private CategoriesDao categoriesDao;

    @Inject
    private CreateProduct createProduct;

    @Inject
    private UpdateProduct updateProduct;

    @POST
    public Response create(ProductCreateDto dto) {
        int id = createProduct.handle(dto);
        return Response.ok(id).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("categoryId") final int categoryId) {
        List<Product> products;
        if (categoryId > 0) {
            products = productsDao.findByCategory(categoryId);
        } else {
            products = productsDao.findAll();
        }

        List<ProductReadDto> result = products
                .stream()
                .map(ProductReadDto::FromProduct)
                .collect(Collectors.toList());

        return Response.ok(result).build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final int id) {
        Product product = productsDao.find(id);
        ProductReadDto dto = ProductReadDto.FromProduct(product);
        return Response.ok(dto).build();
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
        Product product = productsDao.find(id);
        Category category = categoriesDao.find(dto.getCategoryId());
        product.getCategories().add(category);
        return Response.ok(ProductReadDto.FromProduct(product)).build();
    }

    @Path("{id}/categories")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeCategory(@PathParam("id") final int id, ProductAddRemoveCategoryDto dto) {
        Product product = productsDao.find(id);
        Category category = product.getCategories().stream().filter(c -> c.getId() == dto.getCategoryId()).findFirst().get();
        product.getCategories().remove(category);
        return Response.ok(ProductReadDto.FromProduct(product)).build();
    }
}
