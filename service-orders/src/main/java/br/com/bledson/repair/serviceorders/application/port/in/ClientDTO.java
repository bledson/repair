package br.com.bledson.repair.serviceorders.application.port.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ClientDTO(@Email @NotNull String email, String name, String address, String phone) {
}
