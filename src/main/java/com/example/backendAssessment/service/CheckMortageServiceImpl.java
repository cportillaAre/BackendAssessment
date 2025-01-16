package com.example.backendAssessment.service;

import com.example.backendAssessment.dto.MortageDto;
import com.example.backendAssessment.dto.RatesDto;
import com.example.backendAssessment.entity.CheckMortage;
import com.example.backendAssessment.entity.Rates;
import com.example.backendAssessment.exception.BackendAssesmentNotFoundException;
import com.example.backendAssessment.repository.CheckMortageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Service
public class CheckMortageServiceImpl implements CheckMortageService {

    CheckMortageRepository checkMortageRepository;

    public CheckMortageServiceImpl(CheckMortageRepository checkMortageRepository) {
        this.checkMortageRepository = checkMortageRepository;
    }

    @Override
    public MortageDto mortageCheck(int maturityPeriod, BigDecimal income, BigDecimal loanValue, BigDecimal homeValue, List<RatesDto> rates) {
        boolean feasible = isLoanFeasible(income, loanValue) && isLoanValueGraterThanHomeValue(homeValue, loanValue);

        List<RatesDto> filteredRates = rates.stream().filter(rate -> rate.getMaturityPeriod().compareTo(maturityPeriod) == 0).toList();
        if (filteredRates.isEmpty()) {
            throw new BackendAssesmentNotFoundException("The maturity period it is not valid");
        }

        Rates rate = new Rates(filteredRates.get(0).getMaturityPeriod(), filteredRates.get(0).getInterestRate(), filteredRates.get(0).getLastUpdate());
        if (feasible) {
            
            
            CheckMortage checkMortage = new CheckMortage();
            checkMortage.setIncome(income);
            checkMortage.setLoanValue(loanValue);
            checkMortage.setHomeValue(homeValue);
            checkMortage.setMaturityPeriod(rate);
            checkMortageRepository.save(checkMortage);
            log.info("Mortage check saved");
        }
        BigDecimal monthlyCosts = loanValue.multiply(rate.getInterestRate()).divide(BigDecimal.valueOf(rate.getMaturityPeriod() * 12),2, RoundingMode.HALF_UP);
        return new MortageDto(feasible, monthlyCosts);

    }


    private boolean isLoanFeasible(BigDecimal income, BigDecimal loanValue) {
        int feasibleLoan = loanValue.compareTo(income.multiply(BigDecimal.valueOf(4L)));
        if (feasibleLoan == -1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isLoanValueGraterThanHomeValue(BigDecimal homeValue, BigDecimal loanValue) {
        int feasibleLoan = loanValue.compareTo(homeValue);
        if (feasibleLoan <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
