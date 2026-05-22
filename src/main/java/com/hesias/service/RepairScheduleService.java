package com.hesias.service;

import com.hesias.dto.RepairScheduleRequestDto;
import com.hesias.dto.RepairScheduleResponseDto;
import com.hesias.entity.Car;
import com.hesias.entity.Garage;
import com.hesias.entity.RepairSchedule;
import com.hesias.mapper.RepairScheduleMapper;
import com.hesias.repository.CarRepository;
import com.hesias.repository.GarageRepository;
import com.hesias.repository.RepairScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairScheduleService {

    private final RepairScheduleRepository repairScheduleRepository;
    private final GarageRepository garageRepository;
    private final CarRepository carRepository;
    private final RepairScheduleMapper repairScheduleMapper;

    public List<RepairScheduleResponseDto> findByGarageId(Long garageId) {
        return repairScheduleRepository.findByGarageIdOrderByWeekNumberAscRepairOrderAsc(garageId).stream()
                .map(repairScheduleMapper::toResponseDto)
                .toList();
    }

    public List<RepairScheduleResponseDto> findByCarId(Long carId) {
        return repairScheduleRepository.findByCarId(carId).stream()
                .map(repairScheduleMapper::toResponseDto)
                .toList();
    }

    @Transactional
    public RepairScheduleResponseDto create(RepairScheduleRequestDto dto) {
        Garage garage = garageRepository.findById(dto.garageId())
                .orElseThrow(() -> new EntityNotFoundException("Garage not found with id: " + dto.garageId()));
        Car car = carRepository.findById(dto.carId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + dto.carId()));

        RepairSchedule schedule = repairScheduleMapper.toEntity(dto);
        schedule.setGarage(garage);
        schedule.setCar(car);
        return repairScheduleMapper.toResponseDto(repairScheduleRepository.save(schedule));
    }

    @Transactional
    public void delete(Long id) {
        repairScheduleRepository.delete(repairScheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Repair schedule not found with id: " + id)));
    }
}
