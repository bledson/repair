package br.com.bledson.repair.serviceorders.application.domain.service;

import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import br.com.bledson.repair.serviceorders.application.port.in.CreateServiceOrderUseCase;
import br.com.bledson.repair.serviceorders.application.port.out.PublishServiceOrderPort;
import br.com.bledson.repair.serviceorders.application.port.out.SaveServiceOrderPort;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateServiceOrderService implements CreateServiceOrderUseCase {

    private final SaveServiceOrderPort saveServiceOrderPort;
    
    private final PublishServiceOrderPort publishServiceOrderPort;

    public CreateServiceOrderService(SaveServiceOrderPort saveServiceOrderPort,
                                     PublishServiceOrderPort publishServiceOrderPort) {
        this.saveServiceOrderPort = saveServiceOrderPort;
        this.publishServiceOrderPort = publishServiceOrderPort;
    }

    @Override
    public Mono<ServiceOrder> createServiceOrder(final Mono<ServiceOrder> serviceOrderMono) {
        return saveServiceOrderPort.saveServiceOrder(serviceOrderMono)
            .map(serviceOrder -> {
                publishServiceOrderPort.publish(Mono.just(serviceOrder));
                return serviceOrder;
            });
    }
}
