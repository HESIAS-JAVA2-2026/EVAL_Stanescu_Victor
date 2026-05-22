package com.hesias.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "repair_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepairSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id", nullable = false)
    private Garage garage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(nullable = false)
    private Integer weekNumber;

    @Column(nullable = false)
    private Integer repairOrder;

    @Column(nullable = false)
    private LocalDate estimatedStartDate;

    @Column(nullable = false)
    private LocalDate estimatedEndDate;
}
