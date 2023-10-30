package br.com.bledson.repair.serviceorders.adapter.out.persistence;

import br.com.bledson.repair.serviceorders.application.domain.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemPersistenceMapper {

    ItemEntity toEntity(Long serviceOrderId, Item item) {
        return new ItemEntity(item.getType(), item.getBrand(), item.getProblemDescription(), serviceOrderId);
    }

    List<ItemEntity> toEntityList(Long serviceOrderId, List<Item> items) {
        return items.stream().map(item -> toEntity(serviceOrderId, item)).toList();
    }

    Item toModel(final ItemEntity itemEntity) {
        return new Item(itemEntity.getId(),
            itemEntity.getType(),
            itemEntity.getProblemDescription(),
            itemEntity.getBrand(),
            itemEntity.getServiceOrderId());
    }

    List<Item> toModelList(final List<ItemEntity> itemsEntities) {
        return itemsEntities.stream().map(this::toModel).toList();
    }
}
