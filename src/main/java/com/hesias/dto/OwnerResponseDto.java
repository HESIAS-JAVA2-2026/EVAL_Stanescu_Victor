package com.hesias.dto;

import lombok.Builder;

@Builder
public record OwnerResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone
) {}
