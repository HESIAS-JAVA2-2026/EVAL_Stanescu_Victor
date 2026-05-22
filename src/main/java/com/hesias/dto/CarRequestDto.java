package com.hesias.dto;

import lombok.Builder;

@Builder
public record CarRequestDto(
        String licensePlate,
        Integer mileage,
        Integer yearOfService,
        Long ownerId,
        Long garageId
) {}
