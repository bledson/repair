package br.com.bledson.repair.serviceorders.adapter.in.web;

import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import br.com.bledson.repair.serviceorders.application.port.in.CreateServiceOrderCommand;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderWebMapper {

    private final ClientWebMapper clientWebMapper;

    private final ItemWebMapper itemWebMapper;

    public ServiceOrderWebMapper(final ClientWebMapper clientWebMapper, final ItemWebMapper itemWebMapper) {
        this.clientWebMapper = clientWebMapper;
        this.itemWebMapper = itemWebMapper;
    }

    public ServiceOrder toModel(final CreateServiceOrderCommand createServiceOrderCommand) {
        return new ServiceOrder(clientWebMapper.toModel(createServiceOrderCommand.clientDTO()),
            itemWebMapper.toModelList(createServiceOrderCommand.itemsDTO()));
    }
}
