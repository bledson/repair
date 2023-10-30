package br.com.bledson.repair.serviceorders.adapter.out.persistence;

import br.com.bledson.repair.serviceorders.application.domain.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientPersistenceMapper {

    public ClientEntity toEntity(final Client client) {
        return new ClientEntity(client.getEmail(), client.getName(), client.getAddress(), client.getPhone());
    }

    public Client toModel(final ClientEntity clientEntity) {
        return new Client(clientEntity.getId(),
            clientEntity.getEmail(),
            clientEntity.getName(),
            clientEntity.getAddress(),
            clientEntity.getPhone());
    }
}
