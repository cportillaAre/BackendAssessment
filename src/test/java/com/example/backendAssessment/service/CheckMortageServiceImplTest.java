package com.example.backendAssessment.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.backendAssessment.dto.MortageDto;
import com.example.backendAssessment.dto.RatesDto;
import com.example.backendAssessment.repository.CheckMortageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CheckMortageServiceImplTest {
  
  CheckMortageServiceImpl checkMortageService;
  
  @Mock
  CheckMortageRepository checkMortageRepository;
  
  @BeforeEach
  void setup(){
    checkMortageService =  new CheckMortageServiceImpl(checkMortageRepository);
        
  }
  
  @Test
  void mortageCheckTestOk(){
    List<RatesDto> rateList =  new ArrayList<>();
    RatesDto rate =  new RatesDto(10, new BigDecimal("1.49"), OffsetDateTime.now());
    rateList.add(rate);

    //Mockito.doNothing().when(checkMortageRepository).save(Mockito.any(CheckMortage.class));

    MortageDto response =
        checkMortageService.mortageCheck(10, new BigDecimal(50000), new BigDecimal(130000), new BigDecimal(150000), rateList);

    Assertions.assertEquals(new MortageDto(true, new BigDecimal("1614.17")),response);
  }
  
  @Test
  void mortageCheckTestNotFoundRate(){
    List<RatesDto> rateList =  new ArrayList<>();
    try {
      MortageDto response =
          checkMortageService.mortageCheck(10, new BigDecimal(50000), new BigDecimal(130000), new BigDecimal(150000), rateList);
    }catch (Exception e){
      Assertions.assertEquals("The maturity period it is not valid",e.getMessage());
    }
  }
  
  @Test
  void mortageCheckTestKoExceedsFourTimesIncome(){
    List<RatesDto> rateList =  new ArrayList<>();
    RatesDto rate =  new RatesDto(10, new BigDecimal("1.49"), OffsetDateTime.now());
    rateList.add(rate);

    MortageDto response =
        checkMortageService.mortageCheck(10, new BigDecimal(30000), new BigDecimal(130000), new BigDecimal(150000), rateList);
    
    Assertions.assertFalse(response.isFeasible());
  }
  
  @Test
  void mortageCheckKoExceedHomeValue(){
    List<RatesDto> rateList =  new ArrayList<>();
    RatesDto rate =  new RatesDto(10, new BigDecimal("1.49"), OffsetDateTime.now());
    rateList.add(rate);

    MortageDto response =
        checkMortageService.mortageCheck(10, new BigDecimal(30000), new BigDecimal(160000), new BigDecimal(150000), rateList);

    Assertions.assertFalse(response.isFeasible());
  }

}
