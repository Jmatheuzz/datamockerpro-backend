package com.DataMockerPro.DataMockerPro.services;

import com.DataMockerPro.DataMockerPro.interfaces.IValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationRgService implements IValidationService {
    public boolean execute (String rg) {

        return rg.matches("\\d{9}");
    }

}
