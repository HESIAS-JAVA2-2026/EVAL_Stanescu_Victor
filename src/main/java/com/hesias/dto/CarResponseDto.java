package com.hesias.dto;

import lombok.Builder;

@Builder
public record CarResponseDto(
        Long id,
        String licensePlate,
        Integer mileage,
        Integer yearOfService,
        OwnerResponseDto owner
) {}
