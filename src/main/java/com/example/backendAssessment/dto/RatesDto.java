package com.example.backendAssessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class RatesDto {

    private Integer maturityPeriod;
    private BigDecimal interestRate;

    private OffsetDateTime lastUpdate;
}
