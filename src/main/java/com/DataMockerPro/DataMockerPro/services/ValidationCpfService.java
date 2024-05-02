package com.DataMockerPro.DataMockerPro.services;

import com.DataMockerPro.DataMockerPro.interfaces.IValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationCpfService implements IValidationService {
    public boolean execute (String cpf) {

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (verifyAllDigitsEqual(cpf)) {
            return false;
        }

        int digit1 = calcFirstVerifyDigit(cpf);

        if (compareFirstDigit(digit1, cpf)) {
            return false;
        }

        int digit2 = calcSecondVerifyDigit(cpf);

        return !compareSecondDigit(digit2, cpf);
    }
    private static int calcFirstVerifyDigit(String cpf) {
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }
        return digito1;
    }

    private static int calcSecondVerifyDigit(String cpf) {
        int soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }
        return digito2;
    }

    public boolean verifyAllDigitsEqual (String cpf) {
        boolean allDigitsEqual = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                allDigitsEqual = false;
                break;
            }
        }
        return allDigitsEqual;
    }

    public boolean compareFirstDigit(int digit1, String cpf) {

        return digit1 != cpf.charAt(9) - '0';
    }

    public boolean compareSecondDigit(int digit2, String cpf) {

        return digit2 != cpf.charAt(10) - '0';
    }
}
