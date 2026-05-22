package com.hesias.rest;

import com.hesias.dto.RepairScheduleRequestDto;
import com.hesias.dto.RepairScheduleResponseDto;
import com.hesias.service.RepairScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class RepairScheduleController {

    private final RepairScheduleService repairScheduleService;

    @GetMapping("/garage/{garageId}")
    public ResponseEntity<List<RepairScheduleResponseDto>> findByGarageId(@PathVariable Long garageId) {
        return ResponseEntity.ok(repairScheduleService.findByGarageId(garageId));
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<RepairScheduleResponseDto>> findByCarId(@PathVariable Long carId) {
        return ResponseEntity.ok(repairScheduleService.findByCarId(carId));
    }

    @PostMapping
    public ResponseEntity<RepairScheduleResponseDto> create(@RequestBody RepairScheduleRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repairScheduleService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repairScheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
