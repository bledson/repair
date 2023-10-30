package br.com.bledson.repair.serviceorders.adapter.out.persistence;

import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import br.com.bledson.repair.serviceorders.application.port.out.SaveServiceOrderPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ServiceOrderPersistenceAdapter implements SaveServiceOrderPort {

    private final ClientRepository clientRepository;
    private final ItemRepository itemRepository;

    private final ServiceOrderRepository serviceOrderRepository;

    private final ClientPersistenceMapper clientPersistenceMapper;

    private final ItemPersistenceMapper itemPersistenceMapper;

    private final ServiceOrderPersistenceMapper serviceOrderPersistenceMapper;

    public ServiceOrderPersistenceAdapter(ClientRepository clientRepository,
                                          ItemRepository itemRepository,
                                          ServiceOrderRepository serviceOrderRepository,
                                          ClientPersistenceMapper clientPersistenceMapper,
                                          ItemPersistenceMapper itemPersistenceMapper,
                                          ServiceOrderPersistenceMapper serviceOrderPersistenceMapper) {
        this.clientRepository = clientRepository;
        this.itemRepository = itemRepository;
        this.serviceOrderRepository = serviceOrderRepository;
        this.clientPersistenceMapper = clientPersistenceMapper;
        this.itemPersistenceMapper = itemPersistenceMapper;
        this.serviceOrderPersistenceMapper = serviceOrderPersistenceMapper;
    }

    @Override
    public Mono<ServiceOrder> saveServiceOrder(final Mono<ServiceOrder> serviceOrderMono) {
        return serviceOrderMono
            .flatMap(serviceOrder -> clientRepository.save(clientPersistenceMapper.toEntity(serviceOrder.getClient()))
                .flatMap(clientEntity -> serviceOrderRepository.save(
                        serviceOrderPersistenceMapper.toEntity(clientEntity.getId()))
                    .flatMap(serviceOrderEntity -> itemRepository.saveAll(
                            itemPersistenceMapper.toEntityList(serviceOrderEntity.getId(), serviceOrder.getItems()))
                        .collectList()
                        .flatMap(itemsEntitiesList ->
                            Mono.just(serviceOrderPersistenceMapper.toModel(serviceOrderEntity,
                                clientPersistenceMapper.toModel(clientEntity),
                                itemPersistenceMapper.toModelList(itemsEntitiesList)))))));
    }
}
