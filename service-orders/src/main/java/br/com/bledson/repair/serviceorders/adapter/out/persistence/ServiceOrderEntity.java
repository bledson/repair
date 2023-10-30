package br.com.bledson.repair.serviceorders.adapter.out.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "serviceorders")
public class ServiceOrderEntity {

    @Id
    private Long id;

    private final Long clientId;

    public ServiceOrderEntity(final Long clientId) {
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }
}
