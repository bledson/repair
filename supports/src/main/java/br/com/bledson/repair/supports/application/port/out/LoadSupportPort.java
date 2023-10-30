package br.com.bledson.repair.supports.application.port.out;

import br.com.bledson.repair.supports.application.domain.model.Support;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LoadSupportPort {
    Mono<Support> loadSupport(Mono<String> id);
    
    Flux<Support> loadAll();
}
