package br.com.bledson.repair.supports.application.domain.service;

import br.com.bledson.repair.supports.application.domain.model.ServiceOrder;
import br.com.bledson.repair.supports.application.domain.model.Support;
import br.com.bledson.repair.supports.application.domain.model.SupportStatus;
import br.com.bledson.repair.supports.application.port.in.CreateSupportUseCase;
import br.com.bledson.repair.supports.application.port.out.SaveSupportPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateSupportService implements CreateSupportUseCase {

    private final SaveSupportPort saveSupportPort;

    public CreateSupportService(SaveSupportPort saveSupportPort) {
        this.saveSupportPort = saveSupportPort;
    }

    @Override
    public Mono<Support> createSupport(final Mono<ServiceOrder> serviceOrderMono) {
        return saveSupportPort.saveSupport(serviceOrderMono.map(serviceOrder -> {
            Support support = new Support(serviceOrder);
            support.setStatus(SupportStatus.NOT_STARTED);
            return support;
        }));
    }
}
