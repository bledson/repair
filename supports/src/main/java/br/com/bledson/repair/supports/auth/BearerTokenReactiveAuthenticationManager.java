package br.com.bledson.repair.supports.auth;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class BearerTokenReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private final TokenService tokenService;

    private final ReactiveUserDetailsService userDetailsService;

    public BearerTokenReactiveAuthenticationManager(TokenService tokenService,
                                                    ReactiveUserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
            .cast(BearerToken.class)
            .flatMap(auth -> {
                String username = tokenService.getUsername(auth.getCredentials());
                Mono<UserDetails> foundUser = userDetailsService.findByUsername(username);
                return foundUser.flatMap(userDetails -> {
                    if (userDetails.getUsername() == null) {
                        Mono.error(new IllegalArgumentException("user not found"));
                    }
                    if (tokenService.validate(userDetails, auth.getCredentials())) {
                        return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()));
                    }
                    Mono.error(new IllegalArgumentException("Invalid/expired token"));
                    return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()));
                });
            });
    }
}
