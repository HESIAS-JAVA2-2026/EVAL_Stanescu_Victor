package com.hesias.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RepairScheduleRequestDto(
        Long garageId,
        Long carId,
        Integer weekNumber,
        Integer repairOrder,
        LocalDate estimatedStartDate,
        LocalDate estimatedEndDate
) {}
