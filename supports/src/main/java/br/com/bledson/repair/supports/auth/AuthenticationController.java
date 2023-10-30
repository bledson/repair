package br.com.bledson.repair.supports.auth;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final ReactiveUserDetailsService userDetailsService;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(ReactiveUserDetailsService userDetailsService,
                                    TokenService tokenService,
                                    PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        required = true,
        content = @Content(
            schema = @Schema(implementation = AuthenticationDTO.class),
            examples = {
                @ExampleObject(
                    value = """
                        {
                            "username": "agent",
                            "password": "agent"
                        }"""
                )
            }
        )
    )
    public Mono<ResponseEntity<String>> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        return userDetailsService.findByUsername(authenticationDTO.username()).flatMap(userDetails -> {
            if (passwordEncoder.matches(authenticationDTO.password(), userDetails.getPassword())) {
                return Mono.just(ResponseEntity.ok().body(tokenService.generateToken(userDetails.getUsername())));
            }
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials."));
        });
    }
}
