package com.DataMockerPro.DataMockerPro.services;

import com.DataMockerPro.DataMockerPro.interfaces.IGenerateWithNotOptionsService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateRgService implements IGenerateWithNotOptionsService {
    @Override
    public String execute(){
        Random rand = new Random();
        StringBuilder RgNumber = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            RgNumber.append(rand.nextInt(10));
        }

        return RgNumber.toString();
    }
}
