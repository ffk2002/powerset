package com.example.powerset.pset;

import com.example.powerset.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface PSetRepository extends JpaRepository<PSet, Long> {
    Optional<List<PSet>> findAllPSetsByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day);
    Optional<List<PSet>> findAllByTypeAndUser(String type, User user);
    Optional<List<PSet>> findAllByUser(User user);
    Optional<PSet> findByIdAndUser(Long id, User user);
    void deleteByIdAndUser(Long id, User user);
}
