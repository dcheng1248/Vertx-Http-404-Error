import client.HelloClient;
import entity.HELLO_RESPONSE;
import io.quarkus.test.common.WithTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@WithTestResource(MockHelloTestResource.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HelloClientTest {

    @Inject
    HelloClient client;

    @BeforeEach
    void setUp() {
        MockHelloTestResource.resetRequestCount();
    }

    @Test
    void shouldGreetAfterRedirect() {

        // given

        String name = "Bob";
        // when

        HELLO_RESPONSE response = client.getGreeting(name);

        // then
        assertEquals("Yo!", response.getGreeting());
    }

}
