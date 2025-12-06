package org.acme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingResourceTest {

    @Test
    public void hello_returnsGreeting() {
        GreetingResource resource = new GreetingResource();
        String result = resource.hello();
        assertEquals("Hello from Quarkus REST", result);
    }
}
