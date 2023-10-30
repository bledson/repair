package br.com.bledson.repair.supports.adapter.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends ReactiveMongoRepository<SupportEntity, String> {
}
