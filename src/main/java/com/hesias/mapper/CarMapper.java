package com.hesias.mapper;

import com.hesias.dto.CarRequestDto;
import com.hesias.dto.CarResponseDto;
import com.hesias.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {OwnerMapper.class})
public interface CarMapper {

    CarResponseDto toResponseDto(Car car);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "garage", ignore = true)
    Car toEntity(CarRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "garage", ignore = true)
    void update(CarRequestDto dto, @MappingTarget Car car);
}
