package org.acme.resources;

import org.acme.dto.DevOpsRequest;
import org.acme.dto.DevOpsResponse;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

public class DevOpsResourceTest {

    @Test
    public void process_returnsOkAndMessage() {
        DevOpsResource resource = new DevOpsResource();

        DevOpsRequest req = new DevOpsRequest();
        req.to = "Alice";
        req.message = "Hi";

        Response response = resource.process(req);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Object entity = response.getEntity();
        assertNotNull(entity);
        assertTrue(entity instanceof DevOpsResponse);

        DevOpsResponse resp = (DevOpsResponse) entity;
        assertEquals("Hello Alice your message will be send", resp.message);
    }

    @Test
    public void error_returnsBadRequest() {
        DevOpsResource resource = new DevOpsResource();
        Response response = resource.error();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("ERROR", response.getEntity());
    }
}
