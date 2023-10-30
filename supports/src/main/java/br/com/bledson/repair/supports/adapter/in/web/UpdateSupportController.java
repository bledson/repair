package br.com.bledson.repair.supports.adapter.in.web;


import br.com.bledson.repair.supports.application.domain.model.Support;
import br.com.bledson.repair.supports.application.domain.model.SupportStatus;
import br.com.bledson.repair.supports.application.domain.model.Update;
import br.com.bledson.repair.supports.application.port.in.UpdateSupportUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/supports")
@SecurityRequirement(name = "bearerAuth")
public class UpdateSupportController {

    private final UpdateSupportUseCase updateSupportUseCase;

    public UpdateSupportController(final UpdateSupportUseCase updateSupportUseCase) {
        this.updateSupportUseCase = updateSupportUseCase;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<Support>> updateSupport(final @PathVariable String id,
                                                       final @RequestBody Mono<@Valid Update> updateMono,
                                                       final Authentication authentication) {
        return ResponseEntity.ok().body(updateSupportUseCase.updateSupport(Mono.just(id), updateMono,
            Mono.just((String) authentication.getPrincipal())));
    }
}
