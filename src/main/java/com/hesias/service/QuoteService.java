package com.hesias.service;

import com.hesias.dto.QuoteRequestDto;
import com.hesias.dto.QuoteResponseDto;
import com.hesias.entity.Garage;
import com.hesias.entity.Quote;
import com.hesias.mapper.QuoteMapper;
import com.hesias.repository.GarageRepository;
import com.hesias.repository.QuoteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Year;

@Service
@Transactional
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final GarageRepository garageRepository;
    private final QuoteMapper quoteMapper;

    public QuoteResponseDto create(QuoteRequestDto dto) {
        Garage garage = garageRepository.findById(dto.garageId())
                .orElseThrow(() -> new EntityNotFoundException("Garage not found with id: " + dto.garageId()));

        Quote quote = quoteMapper.toEntity(dto);
        quote.setGarage(garage);
        quote.setEstimatedCost(calculateCost(dto.mileage(), dto.yearOfService()));
        return quoteMapper.toResponseDto(quoteRepository.save(quote));
    }

    private BigDecimal calculateCost(int mileage, int yearOfService) {
        int age = Year.now().getValue() - yearOfService;
        double cost = 100.0 + (mileage / 1000.0) * 5 + age * 10;
        return BigDecimal.valueOf(cost);
    }
}
