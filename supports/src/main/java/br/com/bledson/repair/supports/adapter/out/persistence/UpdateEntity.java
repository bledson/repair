package br.com.bledson.repair.supports.adapter.out.persistence;

import java.time.LocalDateTime;

public record UpdateEntity(String description, LocalDateTime addedAt) {
}
