package br.com.bledson.repair.supports.adapter.in.web;

import br.com.bledson.repair.supports.application.domain.model.Support;
import br.com.bledson.repair.supports.application.port.in.StartSupportUseCase;
import br.com.bledson.repair.supports.auth.TokenService;
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
public class StartSupportController {

    private final StartSupportUseCase startSupportUseCase;

    public StartSupportController(final StartSupportUseCase startSupportUseCase) {
        this.startSupportUseCase = startSupportUseCase;
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Mono<Support>> startSupport(final @PathVariable String id,
                                                      final Authentication authentication) {
        return ResponseEntity.ok().body(startSupportUseCase.startSupport(Mono.just(id),
            Mono.just((String) authentication.getPrincipal())));
    }
}
