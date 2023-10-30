package br.com.bledson.repair.supports.adapter.in.messaging;

import br.com.bledson.repair.supports.application.domain.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMessageMapper {

    public Client toModel(final ClientMessage client) {
        return new Client(client.email(), client.name(), client.address(), client.phone());
    }
}
