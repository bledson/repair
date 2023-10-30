package br.com.bledson.repair.serviceorders.application.port.out;

import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import reactor.core.publisher.Mono;

public interface PublishServiceOrderPort {

    void publish(final Mono<ServiceOrder> serviceOrder);
}
