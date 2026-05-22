package com.hesias.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record QuoteResponseDto(
        Long id,
        String licensePlate,
        Integer mileage,
        Integer yearOfService,
        BigDecimal estimatedCost,
        Long garageId,
        String garageName,
        LocalDateTime createdAt
) {}
