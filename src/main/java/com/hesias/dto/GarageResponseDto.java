package com.hesias.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record GarageResponseDto(
        Long id,
        String name,
        String address,
        List<CarResponseDto> stock
) {}
