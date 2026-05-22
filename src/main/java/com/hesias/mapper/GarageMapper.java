package com.hesias.mapper;

import com.hesias.dto.GarageRequestDto;
import com.hesias.dto.GarageResponseDto;
import com.hesias.entity.Garage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface GarageMapper {

    GarageResponseDto toResponseDto(Garage garage);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "repairSchedules", ignore = true)
    Garage toEntity(GarageRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "repairSchedules", ignore = true)
    void update(GarageRequestDto dto, @MappingTarget Garage garage);
}
