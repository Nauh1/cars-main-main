package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CNHValidation implements ConstraintValidator<CNH, String> {

    @Override
    public boolean isValid(String CNH, ConstraintValidatorContext context) {
        return CNH.matches("\\d{11}") && (!CNH.isBlank() || !CNH.equals("null"));
    }

}