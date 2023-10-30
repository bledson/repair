package br.com.bledson.repair.supports.application.port.in;

import br.com.bledson.repair.supports.application.domain.model.ServiceOrder;
import br.com.bledson.repair.supports.application.domain.model.Support;
import reactor.core.publisher.Mono;

public interface CreateSupportUseCase {

    Mono<Support> createSupport(final Mono<ServiceOrder> serviceOrderMono);
}
