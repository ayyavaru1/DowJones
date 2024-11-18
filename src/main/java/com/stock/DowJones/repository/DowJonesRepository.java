package com.stock.DowJones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.DowJones.entity.DowJonesRecord;

import java.util.List;

public interface DowJonesRepository extends JpaRepository<DowJonesRecord, Long> {
    List<DowJonesRecord> findByStock(String stock);
}

 