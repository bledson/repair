package br.com.bledson.repair.supports.application.port.in;

import br.com.bledson.repair.supports.application.domain.model.Support;
import br.com.bledson.repair.supports.application.domain.model.SupportStatus;
import reactor.core.publisher.Mono;

public interface FinishSupportUseCase {
    Mono<Support> finishSupport(final Mono<String> idMono, final Mono<String> usernameMono);
}
