package com.hesias.dto;

import lombok.Builder;

@Builder
public record GarageRequestDto(
        String name,
        String address
) {}
