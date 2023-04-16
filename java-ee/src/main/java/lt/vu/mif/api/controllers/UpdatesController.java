package lt.vu.mif.api.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lt.vu.mif.api.contracts.UpdatesStatusDto;
import lt.vu.mif.api.usecases.CheckForUpdates;

import java.util.concurrent.ExecutionException;

@Path("/updates")
@ApplicationScoped
public class UpdatesController {
    @Inject
    private CheckForUpdates checkForUpdates;

    @POST
    public Response check() {
        checkForUpdates.initiateCheck();
        return Response.accepted().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response status() throws ExecutionException, InterruptedException {
        UpdatesStatusDto dto = new UpdatesStatusDto();
        dto.setChecking(checkForUpdates.isChecking());
        dto.setLastCheckDate(checkForUpdates.getLastCheckDate());
        dto.setUpdateCount(checkForUpdates.getUpdateCount());
        return Response.ok(dto).build();
    }
}
