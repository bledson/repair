package br.com.bledson.repair.serviceorders.application.port.out;

import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import reactor.core.publisher.Mono;

public interface SaveServiceOrderPort {

    Mono<ServiceOrder> saveServiceOrder(final Mono<ServiceOrder> serviceOrderMono);
}
