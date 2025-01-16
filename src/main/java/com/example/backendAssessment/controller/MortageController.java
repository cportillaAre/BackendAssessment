package com.example.backendAssessment.controller;

import com.example.backendAssessment.dto.MortageDto;
import com.example.backendAssessment.dto.RatesDto;
import com.example.backendAssessment.entity.Rates;
import com.example.backendAssessment.exception.BackendAssesmentNotFoundException;
import com.example.backendAssessment.repository.RatesRepository;
import com.example.backendAssessment.service.CheckMortageService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
public class MortageController {
    RatesRepository ratesRepository;
    CheckMortageService checkMortageService;

    public MortageController(RatesRepository ratesRepository, CheckMortageService checkMortageService) {
        this.ratesRepository = ratesRepository;
        this.checkMortageService = checkMortageService;
    }

    @GetMapping("/api/interest-rates")
    @ResponseBody
    public List<RatesDto> getInterestRates() {
        List<RatesDto> response = new ArrayList<>();
        List<Rates> ratesList = ratesRepository.findAll();
        if(ratesList.isEmpty()){
            throw new BackendAssesmentNotFoundException("There is no data in rates");
        }

        ratesList.forEach(rate -> response.add(new RatesDto(rate.getMaturityPeriod(), rate.getInterestRate(), rate.getLastUpdate())));

        return response;
    }

    @PostMapping("/api/mortgage-check")
    @ResponseBody
    public MortageDto mortageCheck(@RequestParam(required = true) int maturityPeriod , @RequestParam(required = true) BigDecimal income,
                                   @RequestParam(required = true) BigDecimal loanValue, @RequestParam(required = true) BigDecimal homeValue) {
        
        List<RatesDto> rates = getInterestRates();

        return checkMortageService.mortageCheck(maturityPeriod,income,loanValue,homeValue,rates);
    }


}
