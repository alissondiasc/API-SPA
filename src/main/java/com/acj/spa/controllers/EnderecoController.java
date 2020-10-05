package com.acj.spa.controllers;




import com.acj.spa.entities.Endereco;
import com.acj.spa.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {
        Endereco enderecoSalvo = enderecoService.salvar(endereco);

        return ResponseEntity.ok(enderecoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> buscarTodos() {
        List<Endereco> enderecos = enderecoService.buscarTodos();

        return ResponseEntity.ok(enderecos);
    }
}
