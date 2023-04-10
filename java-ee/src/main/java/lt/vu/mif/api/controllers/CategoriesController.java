package lt.vu.mif.api.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lt.vu.mif.api.contracts.CategoryReadDto;
import lt.vu.mif.api.usecases.GetAllCategories;

import java.util.List;

@ApplicationScoped
@Path("/categories")
public class CategoriesController {
    @Inject
    private GetAllCategories getAllCategories;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<CategoryReadDto> categories = getAllCategories.handle();
        return Response.ok(categories).build();
    }
}
