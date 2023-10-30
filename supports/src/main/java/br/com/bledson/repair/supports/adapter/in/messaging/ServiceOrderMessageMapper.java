package br.com.bledson.repair.supports.adapter.in.messaging;

import br.com.bledson.repair.supports.application.domain.model.ServiceOrder;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderMessageMapper {

    private final ClientMessageMapper clientMessageMapper;

    private final ItemMessageMapper itemMessageMapper;

    public ServiceOrderMessageMapper(final ClientMessageMapper clientMessageMapper, final ItemMessageMapper itemMessageMapper) {
        this.clientMessageMapper = clientMessageMapper;
        this.itemMessageMapper = itemMessageMapper;
    }

    public ServiceOrder toModel(final ServiceOrderMessage serviceOrderMessage) {
        return new ServiceOrder(serviceOrderMessage.id(),
            clientMessageMapper.toModel(serviceOrderMessage.client()),
            itemMessageMapper.toModel(serviceOrderMessage.items()));
    }
}
