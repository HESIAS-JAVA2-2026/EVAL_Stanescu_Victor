package com.hesias.service;

import com.hesias.dto.OwnerResponseDto;
import com.hesias.entity.Owner;
import com.hesias.mapper.OwnerMapper;
import com.hesias.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMapper ownerMapper;

    @InjectMocks
    private OwnerService ownerService;

    @Test
    void findAll_returnsAllOwners() {
        Owner owner = Owner.builder().id(1L).firstName("Victor").lastName("Stanescu").email("victor@test.com").phone("+123").build();
        OwnerResponseDto dto = new OwnerResponseDto(1L, "Victor", "Stanescu", "victor@test.com", "+123");

        when(ownerRepository.findAll()).thenReturn(List.of(owner));
        when(ownerMapper.toResponseDto(owner)).thenReturn(dto);

        List<OwnerResponseDto> result = ownerService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).firstName()).isEqualTo("Victor");
    }

    @Test
    void findById_returnsOwner() {
        Owner owner = Owner.builder().id(1L).firstName("Victor").lastName("Stanescu").email("victor@test.com").phone("+123").build();
        OwnerResponseDto dto = new OwnerResponseDto(1L, "Victor", "Stanescu", "victor@test.com", "+123");

        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(ownerMapper.toResponseDto(owner)).thenReturn(dto);

        OwnerResponseDto result = ownerService.findById(1L);

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.lastName()).isEqualTo("Stanescu");
    }

    @Test
    void findById_throwsWhenNotFound() {
        when(ownerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ownerService.findById(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }
}