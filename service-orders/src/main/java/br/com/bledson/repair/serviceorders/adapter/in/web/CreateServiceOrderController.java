package br.com.bledson.repair.serviceorders.adapter.in.web;

import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import br.com.bledson.repair.serviceorders.application.port.in.CreateServiceOrderCommand;
import br.com.bledson.repair.serviceorders.application.port.in.CreateServiceOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/service-orders")
public class CreateServiceOrderController {

    private final CreateServiceOrderUseCase createServiceOrderUseCase;

    private final ServiceOrderWebMapper serviceOrderWebMapper;

    public CreateServiceOrderController(final CreateServiceOrderUseCase createServiceOrderUseCase,
                                        final ServiceOrderWebMapper serviceOrderWebMapper) {
        this.createServiceOrderUseCase = createServiceOrderUseCase;
        this.serviceOrderWebMapper = serviceOrderWebMapper;
    }

    @PostMapping
    public ResponseEntity<Mono<ServiceOrder>> create(
        final @RequestBody Mono<@Valid CreateServiceOrderCommand> createServiceOrderCommandMono) {
        Mono<ServiceOrder> serviceOrderMono = createServiceOrderCommandMono
            .flatMap(createServiceOrderCommand -> Mono.just(serviceOrderWebMapper.toModel(createServiceOrderCommand)));
        return ResponseEntity.ok().body(createServiceOrderUseCase.createServiceOrder(serviceOrderMono));
    }
}
