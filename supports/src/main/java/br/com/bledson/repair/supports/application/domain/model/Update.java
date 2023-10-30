package br.com.bledson.repair.supports.application.domain.model;

import java.time.LocalDateTime;

public record Update(String description, LocalDateTime addedAt) {
    public Update(String description) {
        this(description, LocalDateTime.now());
    }
}
