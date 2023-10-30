package br.com.bledson.repair.serviceorders.adapter.out.messaging;

import br.com.bledson.repair.serviceorders.application.domain.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMessageMapper {
    
    public ClientMessage toMessage(final Client client) {
        return new ClientMessage(client.getEmail(), client.getName(), client.getAddress(), client.getPhone());
    }
}
