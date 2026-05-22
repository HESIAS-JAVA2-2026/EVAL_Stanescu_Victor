package com.hesias.mapper;

import com.hesias.dto.OwnerRequestDto;
import com.hesias.dto.OwnerResponseDto;
import com.hesias.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerResponseDto toResponseDto(Owner owner);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    Owner toEntity(OwnerRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    void update(OwnerRequestDto dto, @MappingTarget Owner owner);
}
