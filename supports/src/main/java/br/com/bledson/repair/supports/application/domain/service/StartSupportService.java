package br.com.bledson.repair.supports.application.domain.service;

import br.com.bledson.repair.supports.application.domain.model.Support;
import br.com.bledson.repair.supports.application.domain.model.SupportStatus;
import br.com.bledson.repair.supports.application.domain.model.Update;
import br.com.bledson.repair.supports.application.port.in.StartSupportUseCase;
import br.com.bledson.repair.supports.application.port.out.LoadSupportPort;
import br.com.bledson.repair.supports.application.port.out.SaveSupportPort;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class StartSupportService implements StartSupportUseCase {

    private final LoadSupportPort loadSupportPort;

    private final SaveSupportPort saveSupportPort;

    private final ReactiveUserDetailsService userDetailsService;

    public StartSupportService(LoadSupportPort loadSupportPort,
                               SaveSupportPort saveSupportPort,
                               ReactiveUserDetailsService userDetailsService) {
        this.loadSupportPort = loadSupportPort;
        this.saveSupportPort = saveSupportPort;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Support> startSupport(final Mono<String> idMono, final Mono<String> usernameMono) {
        return saveSupportPort.saveSupport(loadSupportPort.loadSupport(idMono)
            .flatMap(support -> usernameMono.flatMap(userDetailsService::findByUsername)
                .flatMap(user -> {
                    support.setUser(user.getUsername());
                    support.setStatus(SupportStatus.STARTED);
                    support.addUpdate(new Update("Support started", LocalDateTime.now()));
                    return Mono.just(support);
                })));
    }
}
