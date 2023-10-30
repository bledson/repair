package br.com.bledson.repair.supports.adapter.in.web;


import br.com.bledson.repair.supports.application.domain.model.Support;
import br.com.bledson.repair.supports.application.domain.model.SupportStatus;
import br.com.bledson.repair.supports.application.port.in.FinishSupportUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/supports")
@SecurityRequirement(name = "bearerAuth")
public class FinishSupportController {

    private final FinishSupportUseCase finishSupportUseCase;

    public FinishSupportController(final FinishSupportUseCase finishSupportUseCase) {
        this.finishSupportUseCase = finishSupportUseCase;
    }

    @PostMapping("/{id}/finish")
    public ResponseEntity<Mono<Support>> finishSupport(final @PathVariable String id,
                                                       final Authentication authentication) {
        return ResponseEntity.ok().body(finishSupportUseCase.finishSupport(Mono.just(id),
            Mono.just((String) authentication.getPrincipal())));
    }
}
