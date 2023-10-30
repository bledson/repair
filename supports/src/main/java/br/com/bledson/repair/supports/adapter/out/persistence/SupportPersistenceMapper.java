package br.com.bledson.repair.supports.adapter.out.persistence;

import br.com.bledson.repair.supports.application.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SupportPersistenceMapper {
    public SupportEntity toEntity(Support support) {
        return new SupportEntity(
            Objects.nonNull(support.getId())
                ? support.getId()
                : null,
            Objects.nonNull(support.getUser())
                ? support.getUser()
                : null,
            new ServiceOrderEntity(
                support.getServiceOrder().id(),
                new ClientEntity(support.getServiceOrder().id(),
                    support.getServiceOrder().client().email(),
                    support.getServiceOrder().client().name(),
                    support.getServiceOrder().client().address(),
                    support.getServiceOrder().client().phone()),
                support.getServiceOrder().items().stream().map(item ->
                    new ItemEntity(item.type(), item.brand(), item.problemDescription())).toList()),
            Objects.nonNull(support.getUpdates())
                ? support.getUpdates().stream().map(update -> 
                new UpdateEntity(update.description(), update.addedAt())).toList()
                : Arrays.asList(),
            support.getStatus());
    }

    public Support toModel(SupportEntity supportEntity) {
        return new Support(
            supportEntity.getId(),
            supportEntity.getUser(),
            new ServiceOrder(
                supportEntity.getServiceOrder().id(),
                new Client(supportEntity.getServiceOrder().client().email(),
                    supportEntity.getServiceOrder().client().name(),
                    supportEntity.getServiceOrder().client().address(),
                    supportEntity.getServiceOrder().client().phone()),
                supportEntity.getServiceOrder().items().stream().map(itemEntity ->
                    new Item(itemEntity.type(), itemEntity.brand(), itemEntity.problemDescription())).toList()),
            supportEntity.getUpdates().stream().map(updateEntity ->
                new Update(updateEntity.description(), updateEntity.addedAt())).collect(Collectors.toList()),
            supportEntity.getStatus());
    }
}
