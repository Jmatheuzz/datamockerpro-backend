package com.DataMockerPro.DataMockerPro.controllers;

import com.DataMockerPro.DataMockerPro.services.ValidationCpfService;
import com.DataMockerPro.DataMockerPro.services.ValidationRgService;
import com.DataMockerPro.DataMockerPro.types.RequestBodyValidation;
import com.DataMockerPro.DataMockerPro.types.ResponseValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation")
public class ValidationController {

    @Autowired
    private ValidationCpfService validationCpfService;

    @Autowired
    private ValidationRgService validationRgService;

    @PostMapping("/cpf")
    public ResponseEntity<ResponseValidation> validationCpf(@RequestBody RequestBodyValidation data){
        boolean isValid = validationCpfService.execute(data.getValue());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseValidation(isValid));
    }

    @PostMapping("/rg")
    public ResponseEntity<ResponseValidation> validationRg(@RequestBody RequestBodyValidation data){
        boolean isValid = validationRgService.execute(data.getValue());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseValidation(isValid));
    }
}
