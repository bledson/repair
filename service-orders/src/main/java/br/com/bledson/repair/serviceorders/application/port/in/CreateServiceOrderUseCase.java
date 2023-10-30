package br.com.bledson.repair.serviceorders.application.port.in;

import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import reactor.core.publisher.Mono;

public interface CreateServiceOrderUseCase {

    Mono<ServiceOrder> createServiceOrder(final Mono<ServiceOrder> createServiceOrderCommandMono);
}
