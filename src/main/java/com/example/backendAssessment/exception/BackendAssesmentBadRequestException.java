package com.example.backendAssessment.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class BackendAssesmentBadRequestException extends BackendAssesmentException{

    public BackendAssesmentBadRequestException(String s) {
        super(s);
    }
}

