package br.com.bledson.repair.supports.adapter.in.web;

import br.com.bledson.repair.supports.application.domain.model.Support;
import br.com.bledson.repair.supports.application.port.out.LoadSupportPort;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/supports")
@SecurityRequirement(name = "bearerAuth")
public class ReportSupportController {
    
    private final LoadSupportPort loadSupportPort;

    public ReportSupportController(LoadSupportPort loadSupportPort) {
        this.loadSupportPort = loadSupportPort;
    }

    @GetMapping
    public ResponseEntity<Flux<Support>> supportsReport() {
        return ResponseEntity.ok().body(loadSupportPort.loadAll());
    }
}
