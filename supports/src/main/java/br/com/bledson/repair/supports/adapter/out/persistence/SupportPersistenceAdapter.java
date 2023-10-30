package br.com.bledson.repair.supports.adapter.out.persistence;

import br.com.bledson.repair.supports.application.domain.model.Support;
import br.com.bledson.repair.supports.application.port.out.LoadSupportPort;
import br.com.bledson.repair.supports.application.port.out.SaveSupportPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SupportPersistenceAdapter implements LoadSupportPort, SaveSupportPort {

    private final SupportRepository supportRepository;

    private final SupportPersistenceMapper supportPersistenceMapper;

    public SupportPersistenceAdapter(SupportRepository supportRepository,
                                     SupportPersistenceMapper supportPersistenceMapper) {
        this.supportRepository = supportRepository;
        this.supportPersistenceMapper = supportPersistenceMapper;
    }

    @Override
    public Mono<Support> loadSupport(Mono<String> id) {
        return id.flatMap(supportRepository::findById)
            .flatMap(supportEntity -> Mono.just(supportPersistenceMapper.toModel(supportEntity)));
    }

    @Override
    public Flux<Support> loadAll() {
        return supportRepository.findAll().map(supportPersistenceMapper::toModel);
    }

    @Override
    public Mono<Support> saveSupport(Mono<Support> supportMono) {
        return supportMono
            .flatMap(support -> supportRepository.save(supportPersistenceMapper.toEntity(support)))
            .flatMap(supportEntity -> Mono.just(supportPersistenceMapper.toModel(supportEntity)));
    }
}
