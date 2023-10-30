package br.com.bledson.repair.serviceorders.adapter.in.web;

import br.com.bledson.repair.serviceorders.application.domain.model.Client;
import br.com.bledson.repair.serviceorders.application.port.in.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientWebMapper {

    public Client toModel(final ClientDTO clientDTO) {
        return new Client(clientDTO.email(), clientDTO.name(), clientDTO.address(), clientDTO.phone());
    }
}
