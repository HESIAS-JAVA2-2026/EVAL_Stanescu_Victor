package com.hesias.service;

import com.hesias.dto.GarageRequestDto;
import com.hesias.dto.GarageResponseDto;
import com.hesias.entity.Car;
import com.hesias.entity.Garage;
import com.hesias.mapper.GarageMapper;
import com.hesias.repository.CarRepository;
import com.hesias.repository.GarageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GarageService {

    private final GarageRepository garageRepository;
    private final CarRepository carRepository;
    private final GarageMapper garageMapper;

    @Transactional(readOnly = true)
    public List<GarageResponseDto> findAll() {
        return garageRepository.findAll().stream()
                .map(garageMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public GarageResponseDto findById(Long id) {
        return garageMapper.toResponseDto(getOrThrow(id));
    }

    @Transactional(readOnly = true)
    public GarageResponseDto findByName(String name) {
        return garageRepository.findByName(name)
                .map(garageMapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Garage not found with name: " + name));
    }

    public GarageResponseDto create(GarageRequestDto dto) {
        Garage garage = garageMapper.toEntity(dto);
        return garageMapper.toResponseDto(garageRepository.save(garage));
    }

    public GarageResponseDto update(Long id, GarageRequestDto dto) {
        Garage garage = getOrThrow(id);
        garageMapper.update(dto, garage);
        return garageMapper.toResponseDto(garageRepository.save(garage));
    }

    public void delete(Long id) {
        garageRepository.delete(getOrThrow(id));
    }

    public GarageResponseDto addCarToStock(Long garageId, Long carId) {
        Garage garage = getOrThrow(garageId);
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + carId));
        car.setGarage(garage);
        garage.getStock().add(car);
        return garageMapper.toResponseDto(garageRepository.save(garage));
    }

    private Garage getOrThrow(Long id) {
        return garageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Garage not found with id: " + id));
    }
}
