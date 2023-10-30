package br.com.bledson.repair.serviceorders.adapter.out.messaging;

import br.com.bledson.repair.serviceorders.application.domain.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMessageMapper {
    
    public ItemMessage toMessage(final Item item) {
        return new ItemMessage(item.getType(), item.getBrand(), item.getProblemDescription());
    }
    
    public List<ItemMessage> toMessageList(final List<Item> items) {
        return items.stream().map(this::toMessage).toList();
    }
}
