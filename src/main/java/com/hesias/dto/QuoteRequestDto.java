package com.hesias.dto;

import lombok.Builder;

@Builder
public record QuoteRequestDto(
        String licensePlate,
        Integer mileage,
        Integer yearOfService,
        Long garageId
) {}
