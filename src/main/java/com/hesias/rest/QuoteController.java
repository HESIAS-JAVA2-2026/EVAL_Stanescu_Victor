package com.hesias.rest;

import com.hesias.dto.QuoteRequestDto;
import com.hesias.dto.QuoteResponseDto;
import com.hesias.service.QuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quotes")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @PostMapping
    public ResponseEntity<QuoteResponseDto> create(@RequestBody @Valid QuoteRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quoteService.create(dto));
    }
}
