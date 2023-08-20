package com.example.powerset.set;

import com.example.powerset.set.PSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface PSetRepository extends JpaRepository<PSet, Long> {
    Optional<List<PSet>> findAllPSetsByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day);
    Optional<List<PSet>> findAllByType(String type);
}
