package co.com.likeapro.likeaprosqs.models;

import java.time.LocalDateTime;

public record EventSqs(
        Long id,
        String name,
        String description,
        LocalDateTime date,
        Boolean status,
        String customers,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
