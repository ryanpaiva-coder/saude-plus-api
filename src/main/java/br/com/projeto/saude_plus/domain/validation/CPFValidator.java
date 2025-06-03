package br.com.projeto.saude_plus.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPF, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null)
            return false;

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11)
            return false;

        if (cpf.chars().distinct().count() == 1)
            return false;

        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += (cpf.charAt(i) - '0') * (10 - i);
        }
        int resto1 = soma1 % 11;
        int digito1 = (11 - resto1);
        if (digito1 >= 10)
            digito1 = 0;

        if (digito1 != (cpf.charAt(9) - '0'))
            return false;

        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += (cpf.charAt(i) - '0') * (11 - i);
        }
        int resto2 = soma2 % 11;
        int digito2 = (11 - resto2);
        if (digito2 >= 10)
            digito2 = 0;

        if (digito2 != (cpf.charAt(10) - '0'))
            return false;

        return true;
    }
}
