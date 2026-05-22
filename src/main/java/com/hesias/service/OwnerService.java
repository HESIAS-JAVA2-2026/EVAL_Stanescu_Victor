package com.hesias.service;

import com.hesias.dto.OwnerRequestDto;
import com.hesias.dto.OwnerResponseDto;
import com.hesias.entity.Owner;
import com.hesias.mapper.OwnerMapper;
import com.hesias.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Transactional(readOnly = true)
    public List<OwnerResponseDto> findAll() {
        return ownerRepository.findAll().stream()
                .map(ownerMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public OwnerResponseDto findById(Long id) {
        return ownerMapper.toResponseDto(getOrThrow(id));
    }


    public OwnerResponseDto create(OwnerRequestDto dto) {
        Owner owner = ownerMapper.toEntity(dto);
        return ownerMapper.toResponseDto(ownerRepository.save(owner));
    }


    public OwnerResponseDto update(Long id, OwnerRequestDto dto) {
        Owner owner = getOrThrow(id);
        ownerMapper.update(dto, owner);
        return ownerMapper.toResponseDto(ownerRepository.save(owner));
    }


    public void delete(Long id) {
        ownerRepository.delete(getOrThrow(id));
    }

    private Owner getOrThrow(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with id: " + id));
    }
}
