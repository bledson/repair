package br.com.bledson.repair.serviceorders.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<ClientEntity, Long> {
}
