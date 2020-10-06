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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.relation.Role;
import java.util.List;

@Slf4j
@RestController
@SuppressWarnings("java:S4834")
@RequestMapping(CorePathBase.PUBLIC_PATH+"/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @ApiOperation(value = "Cadastrar anuncio.")
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody(required = false) AnuncioDTO anuncioDTO, Authentication authenticatioToken) {
        AnuncioDTO novoAnuncio = anuncioService.cadastrar(anuncioDTO, authenticatioToken.getName());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoAnuncio.getId()).toUri()).build();
    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<Void> deletarMeuAnuncio(@RequestBody String anuncioId, Authentication authenticatioToken) {
        anuncioService.deletarMeuAnuncio(anuncioId, authenticatioToken.getName());
        return ResponseEntity.noContent().build();
    }


    @PostMapping(value = "candidatar/{id}")
    public ResponseEntity<String> candidatar(@PathVariable String id) {
        return new ResponseEntity<>(anuncioService.candidatar(id), HttpStatus.OK);
    }

    @GetMapping(value = "/meus-anuncios")
    public ResponseEntity<List<AnuncioRegistrosDTO>> obterMeusAnuncios(Authentication authenticatioToken) {
        return new ResponseEntity<>(anuncioService.listarMeusAnuncios(authenticatioToken.getName()), HttpStatus.OK);
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/obter-anuncios")
    public ResponseEntity<ListPageDTO> obterAnuncios(@RequestBody FiltroAnunciosDTO filtroAnunciosDTO) {
        return ResponseEntity.ok(anuncioService.filtrarAnuncios(filtroAnunciosDTO));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AnuncioRegistrosDTO> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(ObjectMapperUtils.map(anuncioService.buscarPorId(id), AnuncioRegistrosDTO.class));
    }

    @GetMapping(value = "/candidatos/{id}")
    public ResponseEntity<List<UsuarioSimplificadoDTO>> obterCandidatosPorId(@PathVariable String id) {
        return ResponseEntity.ok(anuncioService.obterCandidatosDoAnuncio(id));
    }

    @PostMapping(value = "/escolher-candidato/{idAnuncio}")
    public ResponseEntity<Void> escolherCandidato(@PathVariable String idAnuncio, @RequestParam String idUsuario) {
        anuncioService.escolherCandidato(idAnuncio, idUsuario);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "{idAnuncio}/finalizar")
    public ResponseEntity<Void> finalizar(@PathVariable String idAnuncio) {
        anuncioService.finalizarServico(idAnuncio);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "{idAnuncio}/encerrar")
    public ResponseEntity<Void> encerrar(@PathVariable String idAnuncio) {
        anuncioService.encerrarServico(idAnuncio);
        return ResponseEntity.noContent().build();
    }

}
