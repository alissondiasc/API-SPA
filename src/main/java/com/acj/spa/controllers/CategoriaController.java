package com.acj.spa.controllers;

import com.acj.spa.entities.Categoria;
import com.acj.spa.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = categoriaService.salvar(categoria);

        return ResponseEntity.ok(categoriaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> buscarTodos() {
        List<Categoria> categorias = categoriaService.buscarTodos();

        return ResponseEntity.ok(categorias);
    }
}
