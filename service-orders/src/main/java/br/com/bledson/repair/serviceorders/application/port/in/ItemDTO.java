package br.com.bledson.repair.serviceorders.application.port.in;

import jakarta.validation.constraints.NotEmpty;

public record ItemDTO(@NotEmpty String type, @NotEmpty String problemDescription, String brand) {
}
