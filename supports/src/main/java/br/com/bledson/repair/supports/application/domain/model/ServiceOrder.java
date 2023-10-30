package br.com.bledson.repair.supports.application.domain.model;

import java.util.List;

public record ServiceOrder(Long id, Client client, List<Item> items) {
}
