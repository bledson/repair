package br.com.bledson.repair.serviceorders.application.port.in;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateServiceOrderCommand(@NotNull @Valid ClientDTO clientDTO, @NotEmpty List<@Valid ItemDTO> itemsDTO) {
}
