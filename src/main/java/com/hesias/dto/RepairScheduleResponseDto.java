package com.hesias.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RepairScheduleResponseDto(
        Long id,
        Long garageId,
        String garageName,
        CarResponseDto car,
        Integer weekNumber,
        Integer repairOrder,
        LocalDate estimatedStartDate,
        LocalDate estimatedEndDate
) {}
