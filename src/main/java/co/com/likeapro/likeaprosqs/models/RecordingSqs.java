package co.com.likeapro.likeaprosqs.models;

import java.sql.Time;
import java.time.LocalDateTime;

public record RecordingSqs(
        Long id,
        String name,
        Long event,
        Time duration,
        Boolean status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
