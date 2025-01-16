package com.example.backendAssessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MortageDto {
    
    private boolean feasible;
    
    private BigDecimal monhtlyCosts;
}
