package com.DataMockerPro.DataMockerPro.controllers;

import com.DataMockerPro.DataMockerPro.services.GenerateCpfService;
import com.DataMockerPro.DataMockerPro.services.GenerateRgService;
import com.DataMockerPro.DataMockerPro.services.ValidationCpfService;
import com.DataMockerPro.DataMockerPro.types.RequestBodyValidation;
import com.DataMockerPro.DataMockerPro.types.ResponseGenerate;
import com.DataMockerPro.DataMockerPro.types.ResponseValidation;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generate")
public class GenerateController {

    @Autowired
    private GenerateCpfService generateCpfService;

    @Autowired
    private GenerateRgService generateRgService;

    @PostMapping("/cpf")
    public ResponseEntity<ResponseGenerate> generateCpf(@PathParam("state") String state){
        String cpf = generateCpfService.execute(state.toLowerCase());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseGenerate(cpf));
    }

    @PostMapping("/rg")
    public ResponseEntity<ResponseGenerate> generateRg(){
        String rg = generateRgService.execute();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseGenerate(rg));
    }
}
