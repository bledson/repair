package br.com.bledson.repair.serviceorders.application.domain.model;

public final class Client {

    private Long id;

    private final String email;

    private String name;

    private String address;

    private String phone;

    public Client(final Long id,
                  final String email,
                  final String name,
                  final String address,
                  final String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Client(final String email,
                  final String name,
                  final String address,
                  final String phone) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }
}
