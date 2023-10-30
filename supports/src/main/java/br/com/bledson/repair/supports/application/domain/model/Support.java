package br.com.bledson.repair.supports.application.domain.model;

import java.util.List;

public class Support {

    private String id;

    private String user;

    private ServiceOrder serviceOrder;

    private List<Update> updates;

    private SupportStatus status;

    public Support() {
    }

    public Support(String id,
                   String user,
                   ServiceOrder serviceOrder,
                   List<Update> updates,
                   SupportStatus status) {
        this.id = id;
        this.user = user;
        this.serviceOrder = serviceOrder;
        this.updates = updates;
        this.status = status;
    }

    public Support(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public List<Update> getUpdates() {
        return updates;
    }

    public void addUpdate(Update update) {
        this.updates.add(update);
    }

    public String getId() {
        return id;
    }

    public SupportStatus getStatus() {
        return status;
    }

    public void setStatus(SupportStatus status) {
        this.status = status;
    }
}
