package br.com.bledson.repair.supports.auth.config;

import br.com.bledson.repair.supports.application.domain.model.Role;
import br.com.bledson.repair.supports.application.domain.model.User;
import br.com.bledson.repair.supports.auth.AuthConverter;
import br.com.bledson.repair.supports.auth.BearerTokenReactiveAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
                                                     AuthConverter authConverter,
                                                     BearerTokenReactiveAuthenticationManager authenticationManager) {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(authenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(authConverter);
        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .authorizeExchange(auth -> auth
                .pathMatchers("/auth/login",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**").permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().hasRole("AGENT"))
            .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails userDetails = new User("agent", passwordEncoder.encode("agent"), Role.ROLE_AGENT);
        return new MapReactiveUserDetailsService(userDetails);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
