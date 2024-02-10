package com.diogo.barbernet.api.controller;

import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.DadosAtulizacaoCabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.DadosCadastroCabeleireiro;
import com.diogo.barbernet.api.domain.cabeleireiro.DadosListagemCabeleireiro;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.diogo.barbernet.api.domain.cliente.DadosAtulizacaoCliente;
import com.diogo.barbernet.api.domain.cliente.DadosCadastroCliente;
import com.diogo.barbernet.api.domain.cliente.DadosListagemCliente;
import com.diogo.barbernet.api.services.CabeleireiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cabeleireiros")
public class CabeleireiroController {
    @Autowired
    private CabeleireiroService service;

    @PostMapping
    public ResponseEntity<DadosCadastroCabeleireiro> cadastrarCabeleireiro (@Valid @RequestBody DadosCadastroCabeleireiro dados){
        Cabeleireiro cabeleireiro = service.cadastrarCabeleireiro(dados);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cabeleireiro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCabeleireiro>> listarCabeleireiro(){
        List<Cabeleireiro> list = service.findAll();
        List<DadosListagemCabeleireiro> listCabeleireiro = list.stream().map(DadosListagemCabeleireiro::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listCabeleireiro);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DadosAtulizacaoCabeleireiro> atualizarCabeleireiro (@PathVariable Long id, @RequestBody @Valid DadosAtulizacaoCabeleireiro dados){
        Cabeleireiro cabeleireiro = service.atualizarCabeleireiro(id, dados);
        return ResponseEntity.ok().body(new DadosAtulizacaoCabeleireiro(cabeleireiro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCabeleireiro(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cabeleireiro " + id + " deletado com sucesso!");
    }

}
