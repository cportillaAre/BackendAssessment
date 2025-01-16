package com.example.backendAssessment.repository;

import com.example.backendAssessment.entity.Rates;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RatesRepository extends JpaRepository<Rates, Integer> {
    
}

