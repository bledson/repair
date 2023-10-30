package br.com.bledson.repair.serviceorders.application.domain.model;

public final class Item {

    private Long id;

    private final String type;

    private final String problemDescription;

    private String brand;

    private Long serviceOrderId;

    public Item(final Long id,
                final String type,
                final String problemDescription,
                final String brand,
                final Long serviceOrderId) {
        this.id = id;
        this.type = type;
        this.problemDescription = problemDescription;
        this.brand = brand;
        this.serviceOrderId = serviceOrderId;
    }

    public Item(final String type, final String problemDescription, final String brand, final Long serviceOrderId) {
        this.type = type;
        this.problemDescription = problemDescription;
        this.brand = brand;
        this.serviceOrderId = serviceOrderId;
    }

    public Item(final String type, final String problemDescription, final String brand) {
        this.type = type;
        this.problemDescription = problemDescription;
        this.brand = brand;
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

    public String getProblemDescription() {
        return problemDescription;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public void setServiceOrderId(final Long serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public Long getServiceOrderId() {
        return serviceOrderId;
    }
}
