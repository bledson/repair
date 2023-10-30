package br.com.bledson.repair.supports.adapter.in.messaging;

import br.com.bledson.repair.supports.application.domain.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMessageMapper {

    public Item toModel(final ItemMessage item) {
        return new Item(item.type(), item.problemDescription(), item.brand());
    }

    public List<Item> toModel(final List<ItemMessage> items) {
        return items.stream().map(this::toModel).toList();
    }
}
