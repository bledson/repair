package br.com.bledson.repair.serviceorders.adapter.out.messaging;

import br.com.bledson.repair.serviceorders.ServiceOrdersApplication;
import br.com.bledson.repair.serviceorders.application.domain.model.Client;
import br.com.bledson.repair.serviceorders.application.domain.model.Item;
import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.cloud.stream.function.StreamBridge;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class ServiceOrderPublisherTest {

    @BeforeAll
    public static void before() {
        System.clearProperty("spring.cloud.function.definition");
    }

    @EnableAutoConfiguration
    public static class EmptyConfiguration {
    }

    @Test
    void testPublish() throws JsonProcessingException {
        try (var context = new SpringApplicationBuilder(TestChannelBinderConfiguration
            .getCompleteConfiguration(ServiceOrdersApplication.class))
            .web(WebApplicationType.NONE)
            .run("--spring.jmx.enabled=false")) {

            var destination = context.getBean(OutputDestination.class);

            var publisher = context.getBean(ServiceOrderPublisher.class);

            var client = new Client("bledson@gmail.com", null, null, null);
            var item = new Item("PC", "broken", null);
            var serviceOrder = new ServiceOrder(1L, client, List.of(item));
            publisher.publish(Mono.just(serviceOrder));
            
            var expected = "{\"id\":1,\"client\":{\"email\":\"bledson@gmail.com\",\"name\":null,\"address\":null,\"phone\":null},\"items\":[{\"type\":\"PC\",\"brand\":null,\"problemDescription\":\"broken\"}]}";

            assertThat(new String(destination.receive(100, "serviceOrders").getPayload()))
                .isEqualTo(expected);
        }
    }
}
