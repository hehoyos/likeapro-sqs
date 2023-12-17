package co.com.likeapro.likeaprosqs.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record StatisticsSqs(
        Long id,
        Timestamp timestamp,
        Long recording,
        Long data,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
