package br.com.bledson.repair.serviceorders.adapter.in.web;

import br.com.bledson.repair.serviceorders.application.domain.model.Item;
import br.com.bledson.repair.serviceorders.application.port.in.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemWebMapper {

    public Item toModel(final ItemDTO itemDTO) {
        return new Item(itemDTO.type(), itemDTO.problemDescription(), itemDTO.brand());
    }

    public List<Item> toModelList(final List<ItemDTO> itemDTOS) {
        return itemDTOS.stream().map(this::toModel).toList();
    }
}
