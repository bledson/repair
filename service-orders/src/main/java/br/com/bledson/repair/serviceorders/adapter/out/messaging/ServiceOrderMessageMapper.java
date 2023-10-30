package br.com.bledson.repair.serviceorders.adapter.out.messaging;

import br.com.bledson.repair.serviceorders.application.domain.model.ServiceOrder;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderMessageMapper {

    private final ClientMessageMapper clientMessageMapper;

    private final ItemMessageMapper itemMessageMapper;

    public ServiceOrderMessageMapper(final ClientMessageMapper clientMessageMapper, final ItemMessageMapper itemMessageMapper) {
        this.clientMessageMapper = clientMessageMapper;
        this.itemMessageMapper = itemMessageMapper;
    }

    public ServiceOrderMessage toMessage(final ServiceOrder serviceOrder) {
        return new ServiceOrderMessage(serviceOrder.getId(),
            clientMessageMapper.toMessage(serviceOrder.getClient()),
            itemMessageMapper.toMessageList(serviceOrder.getItems()));
    }
}
