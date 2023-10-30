package br.com.bledson.repair.supports.application.port.out;

import br.com.bledson.repair.supports.application.domain.model.Support;
import reactor.core.publisher.Mono;

public interface SaveSupportPort {

    Mono<Support> saveSupport(Mono<Support> supportMono);
}
