package com.example.backendAssessment.controller;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.backendAssessment.dto.MortageDto;
import com.example.backendAssessment.dto.RatesDto;
import com.example.backendAssessment.entity.Rates;
import com.example.backendAssessment.repository.RatesRepository;
import com.example.backendAssessment.service.CheckMortageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MortageControllerTest {
  
  MortageController mortageController;
  
  @Mock
  RatesRepository ratesRepository;
  
  @Mock
  CheckMortageServiceImpl checkMortageService;
  
  @BeforeEach()
  public void setup(){
    this.mortageController =  new MortageController(ratesRepository,checkMortageService);
  }
  
  @Test
  void getInterestRatesOk(){
    List<Rates> ratesList =  new ArrayList<>();
    Rates rates =  new Rates(10, new BigDecimal(1.49), OffsetDateTime.now());
    ratesList.add(rates);
    Mockito.when(ratesRepository.findAll()).thenReturn(ratesList);
    List<RatesDto> response = mortageController.getInterestRates();
    Assertions.assertFalse(response.isEmpty());
    
  }

  @Test
  void getInterestRatesNotFound(){
    List<Rates> ratesList =  new ArrayList<>();
    Mockito.when(ratesRepository.findAll()).thenReturn(ratesList);
    try {
      List<RatesDto> response = mortageController.getInterestRates();
    }catch (Exception e){
      Assertions.assertEquals("There is no data in rates",e.getMessage());
    }
  }
  
  @Test
  void mortageCheckTestOk(){
    MortageDto expectedResponse = new MortageDto(true,new BigDecimal(500));
    
    List<Rates> ratesList =  new ArrayList<>();
    Rates rates =  new Rates(10, new BigDecimal(1.49), OffsetDateTime.now());
    ratesList.add(rates);
    Mockito.when(ratesRepository.findAll()).thenReturn(ratesList);
    Mockito.when(checkMortageService.mortageCheck(Mockito.anyInt(),Mockito.any(BigDecimal.class),Mockito.any(BigDecimal.class),
        Mockito.any(BigDecimal.class),Mockito.anyList())).thenReturn(expectedResponse);

    MortageDto response =
        mortageController.mortageCheck(10, new BigDecimal(50000), new BigDecimal(130000), new BigDecimal(150000));
   
    
    Assertions.assertEquals(expectedResponse,response);
    }

    
  }

