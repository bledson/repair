package br.com.bledson.repair.serviceorders.adapter.in.web;

import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import br.com.bledson.repair.serviceorders.application.port.in.CreateServiceOrderUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebFluxTest(CreateServiceOrderController.class)
class CreateServiceOrderControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private CreateServiceOrderUseCase createServiceOrderUseCase;

    @MockBean
    private ServiceOrderWebMapper serviceOrderWebMapper;

    @Test
    void shouldCreate() {
        Mono<ServiceOrder> serviceOrderMono = Mono.just(new ServiceOrder(25L, null, null));
        given(createServiceOrderUseCase.createServiceOrder(any())).willReturn(serviceOrderMono);
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
            .expectBody().jsonPath("$.id").exists();
    }
}
