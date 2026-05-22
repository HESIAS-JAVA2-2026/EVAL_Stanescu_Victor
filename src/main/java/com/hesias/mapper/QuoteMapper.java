package com.hesias.mapper;

import com.hesias.dto.QuoteRequestDto;
import com.hesias.dto.QuoteResponseDto;
import com.hesias.entity.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuoteMapper {

    @Mapping(target = "garageId", source = "garage.id")
    @Mapping(target = "garageName", source = "garage.name")
    QuoteResponseDto toResponseDto(Quote quote);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "garage", ignore = true)
    @Mapping(target = "estimatedCost", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Quote toEntity(QuoteRequestDto dto);
}
