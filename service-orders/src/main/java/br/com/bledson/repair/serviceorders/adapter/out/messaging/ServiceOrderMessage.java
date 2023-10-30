package br.com.bledson.repair.serviceorders.adapter.out.messaging;

import java.util.List;

public record ServiceOrderMessage(Long id, ClientMessage client, List<ItemMessage> items) {
}
