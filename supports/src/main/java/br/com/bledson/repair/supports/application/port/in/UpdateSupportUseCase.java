package br.com.bledson.repair.supports.application.port.in;

import br.com.bledson.repair.supports.application.domain.model.Support;
import br.com.bledson.repair.supports.application.domain.model.SupportStatus;
import br.com.bledson.repair.supports.application.domain.model.Update;
import reactor.core.publisher.Mono;

public interface UpdateSupportUseCase {
    Mono<Support> updateSupport(final Mono<String> idMono, final Mono<Update> updateMono,
                                final Mono<String> usernameMono);
}
