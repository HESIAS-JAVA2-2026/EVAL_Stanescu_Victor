package com.hesias.rest;

import com.hesias.dto.OwnerRequestDto;
import com.hesias.dto.OwnerResponseDto;
import com.hesias.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping
    public ResponseEntity<List<OwnerResponseDto>> findAll() {
        return ResponseEntity.ok(ownerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OwnerResponseDto> create(@RequestBody OwnerRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> update(@PathVariable Long id, @RequestBody OwnerRequestDto dto) {
        return ResponseEntity.ok(ownerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ownerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
