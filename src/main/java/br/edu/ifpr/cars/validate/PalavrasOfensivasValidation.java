package br.edu.ifpr.cars.validate;

import java.util.ArrayList;
import java.util.Arrays;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PalavrasOfensivasValidation implements ConstraintValidator<PalavrasOfensivas, String>{

    final static ArrayList<String> palavrasProibidas = new ArrayList<>(Arrays.asList("lixo","burro","idiota","fdp","imundo"));

    @Override
    public boolean isValid(String palavras, ConstraintValidatorContext context) {
        String[] listaPalavras = palavras.split(" ");
        for (String string : listaPalavras) {
            if (palavrasProibidas.contains(string.toLowerCase())){
                return false;
            }
        }
        return true;
    }


}