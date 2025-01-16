package com.example.backendAssessment.service;

import com.example.backendAssessment.dto.MortageDto;
import com.example.backendAssessment.dto.RatesDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

public interface CheckMortageService {

    MortageDto mortageCheck(int maturityPeriod , BigDecimal income, BigDecimal loanValue, BigDecimal homeValue, List<RatesDto> rates);
}
