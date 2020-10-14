package com.acj.spa.controller;

import com.acj.spa.config.paths.CorePathBase;
import com.acj.spa.dto.*;
import com.acj.spa.service.AnuncioService;
import com.acj.spa.util.ObjectMapperUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@SuppressWarnings("java:S4834")
@RequestMapping(CorePathBase.PUBLIC_PATH + "/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @ApiOperation(value = "Cadastrar anuncio")
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody(required = true) final AnuncioDTO anuncioDTO) {
        return ResponseEntity.ok(anuncioService.cadastrar(anuncioDTO));
    }

    @ApiOperation(value = "Deletar anuncio")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/deletar")
    public ResponseEntity<Void> deletarMeuAnuncio(@RequestBody String anuncioId) {
        anuncioService.deletarMeuAnuncio(anuncioId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Candidatar usuário logado em algum anuncio.")
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "candidatar/{id}")
    public ResponseEntity<String> candidatar(@PathVariable String id) {
        return new ResponseEntity<>(anuncioService.candidatar(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Obter todos anuncio do usuário logado.")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/meus-anuncios")
    public ResponseEntity<List<AnuncioRegistrosDTO>> obterMeusAnuncios() {
        return ResponseEntity.ok(anuncioService.listarMeusAnuncios());
    }

    @ApiOperation(value = "Obter todos anuncios com filtro.")
    @PostMapping(value = "/obter-anuncios")
    public ResponseEntity<ListPageDTO> obterAnuncios(@RequestBody FiltroAnunciosDTO filtroAnunciosDTO) {
        return ResponseEntity.ok(anuncioService.filtrarAnuncios(filtroAnunciosDTO));
    }

    @ApiOperation(value = "Obter anuncio por ID")
    @GetMapping(value = "{id}")
    public ResponseEntity<AnuncioRegistrosDTO> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(ObjectMapperUtils.map(anuncioService.buscarPorId(id), AnuncioRegistrosDTO.class));
    }

    @ApiOperation(value = "Obter candidatos por ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/candidatos/{id}")
    public ResponseEntity<List<UsuarioSimplificadoDTO>> obterCandidatosPorId(@PathVariable String id) {
        return ResponseEntity.ok(anuncioService.obterCandidatosDoAnuncio(id));
    }

    @ApiOperation(value = "Escolher candidatos para realização do serviço.")
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/escolher-candidato/{idAnuncio}")
    public ResponseEntity<Void> escolherCandidato(@PathVariable String idAnuncio, @RequestParam String idUsuario) {
        anuncioService.escolherCandidato(idAnuncio, idUsuario);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Finalizar serviço.")
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "{idAnuncio}/finalizar")
    public ResponseEntity<Void> finalizar(@PathVariable String idAnuncio) {
        anuncioService.finalizarServico(idAnuncio);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Encerrar serviço.")
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "{idAnuncio}/encerrar")
    public ResponseEntity<Void> encerrar(@PathVariable String idAnuncio) {
        anuncioService.encerrarServico(idAnuncio);
        return ResponseEntity.noContent().build();
    }

}
