package br.com.bledson.repair.supports.adapter.out.persistence;

import br.com.bledson.repair.supports.application.domain.model.SupportStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("supports")
public class SupportEntity {

    @Id
    private String id;

    private String user;

    private ServiceOrderEntity serviceOrder;

    private List<UpdateEntity> updates;

    private SupportStatus status;

    public SupportEntity() {
    }

    public SupportEntity(String id,
                         String user,
                         ServiceOrderEntity serviceOrder,
                         List<UpdateEntity> updates,
                         SupportStatus status) {
        this.id = id;
        this.user = user;
        this.serviceOrder = serviceOrder;
        this.updates = updates;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public ServiceOrderEntity getServiceOrder() {
        return serviceOrder;
    }

    public List<UpdateEntity> getUpdates() {
        return updates;
    }

    public SupportStatus getStatus() {
        return status;
    }
}
