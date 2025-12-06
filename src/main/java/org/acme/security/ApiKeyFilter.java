package org.acme.security;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;

@Provider
public class ApiKeyFilter implements ContainerRequestFilter {

    @Inject
    @ConfigProperty(name ="api-key")
    String VALID_API_KEY;

    private static final String API_KEY_HEADER = "X-Parse-REST-API-Key";
 
    @Override
    public void filter(ContainerRequestContext requestContext) {
        String apiKey = requestContext.getHeaderString(API_KEY_HEADER);
       
        if (apiKey == null || !apiKey.equals(VALID_API_KEY)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("ERROR")
                    .build());
        }
    }
}