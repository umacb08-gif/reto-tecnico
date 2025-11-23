package org.acme.resources;

import org.acme.dto.DevOpsRequest;
import org.acme.dto.DevOpsResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/DevOps")
public class DevOpsResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response process(DevOpsRequest request) {

        String msg = "Hello " + request.to + " your message will be send";

        DevOpsResponse resp = new DevOpsResponse(msg);

        return Response.ok(resp).build();
    }

    @GET
    @PUT
    @DELETE
    @PATCH
    @HEAD
    @OPTIONS
    public Response error() {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("ERROR")
                .build();
    }
}