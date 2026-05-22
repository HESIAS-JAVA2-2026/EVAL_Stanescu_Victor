package com.hesias.service;

import com.hesias.dto.CarRequestDto;
import com.hesias.dto.CarResponseDto;
import com.hesias.entity.Car;
import com.hesias.entity.Owner;
import com.hesias.mapper.CarMapper;
import com.hesias.repository.CarRepository;
import com.hesias.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;
    private final CarMapper carMapper;

    @Transactional(readOnly = true)
    public CarResponseDto findById(Long id) {
        return carMapper.toResponseDto(getOrThrow(id));
    }

    @Transactional(readOnly = true)
    public CarResponseDto findByLicensePlate(String licensePlate) {
        return carRepository.findByLicensePlate(licensePlate)
                .map(carMapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with license plate: " + licensePlate));
    }

    public CarResponseDto create(CarRequestDto dto) {
        Owner owner = ownerRepository.findById(dto.ownerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with id: " + dto.ownerId()));
        Car car = carMapper.toEntity(dto);
        car.setOwner(owner);
        return carMapper.toResponseDto(carRepository.save(car));
    }

    private Car getOrThrow(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
    }
}
