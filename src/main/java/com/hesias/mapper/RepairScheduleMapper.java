package com.hesias.mapper;

import com.hesias.dto.RepairScheduleRequestDto;
import com.hesias.dto.RepairScheduleResponseDto;
import com.hesias.entity.RepairSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface RepairScheduleMapper {

    @Mapping(target = "garageId", source = "garage.id")
    @Mapping(target = "garageName", source = "garage.name")
    RepairScheduleResponseDto toResponseDto(RepairSchedule repairSchedule);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "garage", ignore = true)
    @Mapping(target = "car", ignore = true)
    RepairSchedule toEntity(RepairScheduleRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "garage", ignore = true)
    @Mapping(target = "car", ignore = true)
    void update(RepairScheduleRequestDto dto, @MappingTarget RepairSchedule repairSchedule);
}
