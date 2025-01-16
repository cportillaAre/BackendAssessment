package com.example.backendAssessment.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class BackendAssesmentNotFoundException extends BackendAssesmentException{

    public BackendAssesmentNotFoundException(String s) {
        super(s);
    }
}
