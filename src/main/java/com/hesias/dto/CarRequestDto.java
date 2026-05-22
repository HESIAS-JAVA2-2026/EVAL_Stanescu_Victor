package com.hesias.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public record CarRequestDto(
        String licensePlate,
        Integer mileage,
        Integer yearOfService,
        Long ownerId,
        Long garageId
) {}
