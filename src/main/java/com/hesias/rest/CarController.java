package com.hesias.rest;

import com.hesias.dto.CarRequestDto;
import com.hesias.dto.CarResponseDto;
import com.hesias.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @GetMapping("/license/{licensePlate}")
    public ResponseEntity<CarResponseDto> findByLicensePlate(@PathVariable String licensePlate) {
        return ResponseEntity.ok(carService.findByLicensePlate(licensePlate));
    }

    @PostMapping
    public ResponseEntity<CarResponseDto> create(@RequestBody CarRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.create(dto));
    }
}
