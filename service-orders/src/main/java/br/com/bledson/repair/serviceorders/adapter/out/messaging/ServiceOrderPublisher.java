package br.com.bledson.repair.serviceorders.adapter.out.messaging;

import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import br.com.bledson.repair.serviceorders.application.port.out.PublishServiceOrderPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ServiceOrderPublisher implements PublishServiceOrderPort {

    private final StreamBridge streamBridge;

    private final String destination;
    
    private final ServiceOrderMessageMapper serviceOrderMessageMapper;

    public ServiceOrderPublisher(final StreamBridge streamBridge,
                                 final @Value("${spring.cloud.stream.output-bindings}") String destination,
                                 final ServiceOrderMessageMapper serviceOrderMessageMapper) {
        this.streamBridge = streamBridge;
        this.destination = destination;
        this.serviceOrderMessageMapper = serviceOrderMessageMapper;
    }

    public void publish(final Mono<ServiceOrder> serviceOrderMono) {
        serviceOrderMono.map(serviceOrder -> {
            streamBridge.send(destination, serviceOrderMessageMapper.toMessage(serviceOrder));
            return serviceOrder;
        }).subscribe();
    }
}
