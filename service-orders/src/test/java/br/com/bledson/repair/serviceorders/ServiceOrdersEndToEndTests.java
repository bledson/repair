package br.com.bledson.repair.serviceorders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ServiceOrdersEndToEndTests {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Container
    @ServiceConnection
    static RabbitMQContainer rabbit = new RabbitMQContainer("rabbitmq:latest");

    @Test
    void testCreate(@Autowired WebTestClient client) {
        var json = """
            {
              "clientDTO": {
                "email": "bledson@gmail.com"
              },
              "itemsDTO": [
                {
                  "type": "PC",
                  "problemDescription": "broken"
                }
              ]
            }""";
        client.post()
            .uri("/service-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(json))
            .exchange()
            .expectStatus().isOk()
            .expectBody().jsonPath("$.id").isEqualTo(1L);
    }
}
