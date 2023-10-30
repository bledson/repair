package br.com.bledson.repair.supports.adapter.in.messaging;

import java.util.List;

public record ServiceOrderMessage(Long id, ClientMessage client, List<ItemMessage> items) {
}
