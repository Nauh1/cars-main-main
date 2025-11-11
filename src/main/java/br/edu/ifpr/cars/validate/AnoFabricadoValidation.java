package br.edu.ifpr.cars.validate;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AnoFabricadoValidation implements ConstraintValidator<AnoFabricado, Integer>{

    @Override
    public boolean isValid(Integer AnoFabricado, ConstraintValidatorContext context) {
        return AnoFabricado > 1886 && AnoFabricado < LocalDate.now().getYear();
    }


}