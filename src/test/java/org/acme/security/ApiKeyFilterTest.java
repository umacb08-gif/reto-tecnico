package org.acme.security;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ApiKeyFilterTest {

    private ApiKeyFilter filter;

    @BeforeEach
    public void setUp() throws Exception {
        filter = new ApiKeyFilter();
        java.lang.reflect.Field f = ApiKeyFilter.class.getDeclaredField("VALID_API_KEY");
        f.setAccessible(true);
        f.set(filter, "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c");
    }

    @Test
    public void filter_abortsWhenMissingHeader() {
        ContainerRequestContext ctx = mock(ContainerRequestContext.class);
        when(ctx.getHeaderString("X-Parse-REST-API-Key")).thenReturn(null);

        filter.filter(ctx);

        verify(ctx).abortWith(Mockito.argThat(resp -> resp.getStatus() == Response.Status.UNAUTHORIZED.getStatusCode()));
    }

    @Test
    public void filter_abortsWhenInvalid() {
        ContainerRequestContext ctx = mock(ContainerRequestContext.class);
        when(ctx.getHeaderString("X-Parse-REST-API-Key")).thenReturn("bad");

        filter.filter(ctx);

        verify(ctx).abortWith(Mockito.argThat(resp -> resp.getStatus() == Response.Status.UNAUTHORIZED.getStatusCode()));
    }

    @Test
    public void filter_allowsWhenValid() {
        ContainerRequestContext ctx = mock(ContainerRequestContext.class);
        when(ctx.getHeaderString("X-Parse-REST-API-Key")).thenReturn("2f5ae96c-b558-4c7b-a590-a501ae1c3f6c");

        filter.filter(ctx);

        verify(ctx, never()).abortWith(any());
    }
}
