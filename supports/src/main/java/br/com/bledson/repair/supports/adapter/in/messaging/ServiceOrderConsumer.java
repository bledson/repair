package br.com.bledson.repair.supports.adapter.in.messaging;

import br.com.bledson.repair.supports.application.port.in.CreateSupportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Component
public class ServiceOrderConsumer {

    private final CreateSupportUseCase createSupportUseCase;

    private final ServiceOrderMessageMapper serviceOrderMessageMapper;

    public ServiceOrderConsumer(CreateSupportUseCase createSupportUseCase,
                                ServiceOrderMessageMapper serviceOrderMessageMapper) {
        this.createSupportUseCase = createSupportUseCase;
        this.serviceOrderMessageMapper = serviceOrderMessageMapper;
    }

    @Bean
    public Consumer<ServiceOrderMessage> consumeServiceOrder() {
        return serviceOrder -> createSupportUseCase
            .createSupport(Mono.just(serviceOrderMessageMapper.toModel(serviceOrder))).subscribe();
    }
}
