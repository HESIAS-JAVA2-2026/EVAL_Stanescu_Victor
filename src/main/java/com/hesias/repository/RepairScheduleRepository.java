package com.hesias.repository;

import com.hesias.entity.RepairSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairScheduleRepository extends JpaRepository<RepairSchedule, Long> {

    List<RepairSchedule> findByGarageIdOrderByWeekNumberAscRepairOrderAsc(Long garageId);

    List<RepairSchedule> findByCarId(Long carId);
}
