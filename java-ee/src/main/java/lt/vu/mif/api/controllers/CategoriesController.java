package lt.vu.mif.api.controllers;

import lt.vu.mif.api.persistence.CategoriesRepository;
import lt.vu.mif.api.contracts.CategoryReadDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/categories")
public class CategoriesController {
    @Inject
    private CategoriesRepository categoriesRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<CategoryReadDto> categories = categoriesRepository
                .findAll()
                .stream()
                .map(CategoryReadDto::FromCategory)
                .collect(Collectors.toList());

        return Response.ok(categories).build();
    }
}
