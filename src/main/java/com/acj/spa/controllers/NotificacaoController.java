package com.acj.spa.controllers;

import com.acj.spa.services.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "notificaca" )
public class NotificacaoController {
    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping(value = "/nao-lidas/id-usuario/{idUsuario}")
    public ResponseEntity obterNotificacaoNaoLidas(String idUsuario) {
        try {
            return new ResponseEntity<>(notificacaoService.obterNotificacoesNaoLidas(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
