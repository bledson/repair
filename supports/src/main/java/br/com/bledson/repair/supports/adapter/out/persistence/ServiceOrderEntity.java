package br.com.bledson.repair.supports.adapter.out.persistence;

import java.util.List;

public record ServiceOrderEntity(Long id, ClientEntity client, List<ItemEntity> items) {
}
