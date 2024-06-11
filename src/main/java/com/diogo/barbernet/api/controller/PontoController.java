package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.ponto.DadosDeEntrada;
import com.diogo.barbernet.api.services.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ponto")
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @PostMapping("entrada")
    private ResponseEntity entrada (@RequestBody DadosDeEntrada dados) {
        this.pontoService.entrada(dados);
        return ResponseEntity.ok().build();
    }

}
