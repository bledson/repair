package br.com.bledson.repair.serviceorders.application.domain.model;

import java.beans.Transient;
import java.util.List;

public final class ServiceOrder {

    private Long id;

    private final Client client;

    private final List<Item> items;

    public ServiceOrder(final Long id, final Client client, final List<Item> items) {
        this.id = id;
        this.client = client;
        this.items = items;
    }

    public ServiceOrder(final Client client, final List<Item> items) {
        this.client = client;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    @Transient
    public Long getClientId() {
        return client.getId();
    }

    public List<Item> getItems() {
        return items;
    }
}
