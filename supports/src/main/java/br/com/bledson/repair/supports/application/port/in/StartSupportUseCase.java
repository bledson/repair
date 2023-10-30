package br.com.bledson.repair.supports.application.port.in;

import br.com.bledson.repair.supports.application.domain.model.Support;
import reactor.core.publisher.Mono;

public interface StartSupportUseCase {

    Mono<Support> startSupport(final Mono<String> idMono, final Mono<String> usernameMono);
}
