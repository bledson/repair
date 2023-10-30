package br.com.bledson.repair.serviceorders.application.domain.service;

import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import br.com.bledson.repair.serviceorders.application.port.out.PublishServiceOrderPort;
import br.com.bledson.repair.serviceorders.application.port.out.SaveServiceOrderPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CreateServiceOrderServiceTest {

    @InjectMocks
    private CreateServiceOrderService service;

    @Mock
    private SaveServiceOrderPort saveServiceOrderPort;

    @Mock
    private PublishServiceOrderPort publishServiceOrderPort;

    @Test
    void shouldCreate() {
        Mono<ServiceOrder> serviceOrderMono = Mono.just(new ServiceOrder(25L, null, null));
        given(saveServiceOrderPort.saveServiceOrder(any())).willReturn(serviceOrderMono);

        StepVerifier
            .create(service.createServiceOrder(Mono.just(new ServiceOrder(null, null))))
            .expectNextMatches(serviceOrder -> serviceOrder.getId().equals(25L))
            .verifyComplete();
    }
}
