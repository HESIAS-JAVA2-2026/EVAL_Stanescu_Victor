package com.hesias.dto;

import lombok.Builder;

@Builder
public record OwnerRequestDto(
        String firstName,
        String lastName,
        String email,
        String phone
) {}
