package br.com.bledson.repair.serviceorders.adapter.out.persistence;

import br.com.bledson.repair.serviceorders.application.domain.model.Client;
import br.com.bledson.repair.serviceorders.application.domain.model.Item;
import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceOrderPersistenceMapper {

    ServiceOrderEntity toEntity(final Long clientId) {
        return new ServiceOrderEntity(clientId);
    }

    ServiceOrder toModel(final ServiceOrderEntity serviceOrderEntity,
                         final Client client,
                         final List<Item> items) {
        return new ServiceOrder(serviceOrderEntity.getId(), client, items);
    }
}
