package com.DataMockerPro.DataMockerPro.services;

import com.DataMockerPro.DataMockerPro.interfaces.IGenerateService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateCpfService implements IGenerateService {

    @Override
    public String execute(String state) {

        Random generator = new Random();
        int[] cpfArray = new int[11];

        // Generating the first 9 digits of CPF
        for (int i = 0; i < 9; i++) {
            cpfArray[i] = generator.nextInt(10);
        }
        if (state != null) cpfArray[8] = defineStateDigit(state) >= 0 ? defineStateDigit(state) : cpfArray[8];
            // Calculating the first verification digit
        cpfArray[9] = calculateVerificationDigit(cpfArray, 9);

        // Calculating the second verification digit
        cpfArray[10] = calculateVerificationDigit(cpfArray, 10);

        // Formatting the CPF
        StringBuilder formattedCPF = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            formattedCPF.append(cpfArray[i]);
            if (i == 2 || i == 5) {
                formattedCPF.append(".");
            } else if (i == 8) {
                formattedCPF.append("-");
            }
        }

        return formattedCPF.toString();
    }

    public int calculateVerificationDigit(int[] cpf, int position) {
        int sum = 0;
        int weight = position + 1;
        for (int i = 0; i < position; i++) {
            sum += cpf[i] * weight;
            weight--;
        }
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : (11 - remainder);
    }

    public int defineStateDigit(String state) {
        return switch (state.toLowerCase()) {
            case "df", "to", "mt", "ms", "go" -> 1;
            case "ac", "am", "rr", "ro", "pa", "ap" -> 2;
            case "ce", "pi", "ma" -> 3;
            case "al", "rn", "pe", "pb" -> 4;
            case "ba", "se" -> 5;
            case "mg" -> 6;
            case "es", "rj" -> 7;
            case "sp" -> 8;
            case "pr", "sc" -> 9;
            case "rs" -> 0;
            default -> -1;
        };
    }
}
