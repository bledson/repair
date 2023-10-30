package br.com.bledson.repair.supports.application.domain.service;

import br.com.bledson.repair.supports.application.domain.model.Support;
import br.com.bledson.repair.supports.application.domain.model.SupportStatus;
import br.com.bledson.repair.supports.application.domain.model.Update;
import br.com.bledson.repair.supports.application.port.in.FinishSupportUseCase;
import br.com.bledson.repair.supports.application.port.out.LoadSupportPort;
import br.com.bledson.repair.supports.application.port.out.SaveSupportPort;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class FinishSupportService implements FinishSupportUseCase {

    private final LoadSupportPort loadSupportPort;

    private final SaveSupportPort saveSupportPort;

    private final ReactiveUserDetailsService userDetailsService;

    public FinishSupportService(LoadSupportPort loadSupportPort,
                                SaveSupportPort saveSupportPort,
                                ReactiveUserDetailsService userDetailsService) {
        this.loadSupportPort = loadSupportPort;
        this.saveSupportPort = saveSupportPort;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Support> finishSupport(final Mono<String> idMono, final Mono<String> usernameMono) {
        return saveSupportPort.saveSupport(loadSupportPort.loadSupport(idMono)
            .flatMap(support -> usernameMono.flatMap(userDetailsService::findByUsername)
                .flatMap(user -> {
                    support.setUser(user.getUsername());
                    support.setStatus(SupportStatus.FINISHED);
                    support.addUpdate(new Update("Support finished", LocalDateTime.now()));
                    return Mono.just(support);
                })));
    }
}
