package com.example.backendAssessment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BackendAssesmentException extends RuntimeException{

    protected String message;

    
}
