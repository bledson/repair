package br.com.bledson.repair.serviceorders.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOrderRepository extends ReactiveCrudRepository<ServiceOrderEntity, Long> {
}
