package br.com.bledson.repair.supports.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    private final Algorithm algorithm;

    public TokenService(@Value("${security.token.secret}") String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(String username) {
        try {
            return JWT.create()
                .withIssuer("auth-api")
                .withSubject(username)
                .withExpiresAt(Instant.now().plus(90L, ChronoUnit.DAYS))
                .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public boolean validate(UserDetails userDetails, String token) {
        return getUsername(token).equals(userDetails.getUsername());
    }

    public String getUsername(String token) {
        try {
            return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }
}
