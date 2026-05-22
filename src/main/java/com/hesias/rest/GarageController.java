package com.hesias.rest;

import com.hesias.dto.GarageRequestDto;
import com.hesias.dto.GarageResponseDto;
import com.hesias.service.GarageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garages")
@RequiredArgsConstructor
public class GarageController {

    private final GarageService garageService;

    @GetMapping
    public ResponseEntity<List<GarageResponseDto>> findAll() {
        return ResponseEntity.ok(garageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarageResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(garageService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GarageResponseDto> findByName(@PathVariable String name) {
        return ResponseEntity.ok(garageService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<GarageResponseDto> create(@RequestBody @Valid GarageRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(garageService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarageResponseDto> update(@PathVariable Long id, @RequestBody @Valid GarageRequestDto dto) {
        return ResponseEntity.ok(garageService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        garageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{garageId}/cars/{carId}")
    public ResponseEntity<GarageResponseDto> addCarToStock(@PathVariable Long garageId, @PathVariable Long carId) {
        return ResponseEntity.ok(garageService.addCarToStock(garageId, carId));
    }

    @GetMapping("/{garageId}/schedule")
    public ResponseEntity<?> getSchedule(@PathVariable Long garageId) {
        return ResponseEntity.ok(garageService.findById(garageId));
    }
}
