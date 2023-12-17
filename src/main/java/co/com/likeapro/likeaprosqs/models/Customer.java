package co.com.likeapro.likeaprosqs.models;

import java.time.LocalDateTime;

public record Customer (
    Long id,
    String name,
    String email,
    String password,
    String phone,
    String role,
    Boolean status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
