package br.com.projeto.saude_plus.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CNPJValidator implements ConstraintValidator<CNPJ, String> {

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (cnpj == null)
            return false;

        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14)
            return false;

        if (cnpj.chars().distinct().count() == 1)
            return false;

        int[] pesos1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

        int soma1 = 0;

        for (int i = 0; i < 12; i++) {
            soma1 += (cnpj.charAt(i) - '0') * pesos1[i];
        }

        int resto1 = soma1 % 11;
        int digito1 = (resto1 < 2) ? 0 : 11 - resto1;

        if (digito1 != (cnpj.charAt(12) - '0'))
            return false;

        int[] pesos2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

        int soma2 = 0;

        for (int i = 0; i < 13; i++) {
            soma2 += (cnpj.charAt(i) - '0') * pesos2[i];
        }

        int resto2 = soma2 % 11;

        int digito2 = (resto2 < 2) ? 0 : 11 - resto2;

        if (digito2 != (cnpj.charAt(13) - '0'))
            return false;

        return true;
    }
}
