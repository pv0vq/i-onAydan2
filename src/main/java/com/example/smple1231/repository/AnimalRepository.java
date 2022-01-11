package com.example.smple1231.repository;

import com.example.smple1231.membervo.AnimalIns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<AnimalIns, Long> {
}
