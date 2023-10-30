package br.com.bledson.repair.serviceorders.adapter.out.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "items")
public class ItemEntity {

    @Id
    private Long id;

    private final String type;

    private String brand;

    @Column("problemdescription")
    private final String problemDescription;

    @Column("serviceorder_id")
    private final Long serviceOrderId;

    public ItemEntity(final String type, final String problemDescription, final Long serviceOrderId) {
        this.type = type;
        this.problemDescription = problemDescription;
        this.serviceOrderId = serviceOrderId;
    }

    public ItemEntity(final String type,
                      final String brand,
                      final String problemDescription,
                      final Long serviceOrderId) {
        this.type = type;
        this.brand = brand;
        this.problemDescription = problemDescription;
        this.serviceOrderId = serviceOrderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public Long getServiceOrderId() {
        return serviceOrderId;
    }
}
