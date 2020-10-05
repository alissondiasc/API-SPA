package com.acj.spa.controllers;
import com.acj.spa.dto.*;
import com.acj.spa.dto.parser.AvaliacaoParser;
import com.acj.spa.dto.parser.DadosProfissionaisParser;
import com.acj.spa.dto.parser.UsuarioParser;
import com.acj.spa.entities.Usuario;
import com.acj.spa.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import java.util.List;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
    	usuarioDTO.setSenha(usuarioService.encpritografarBcripty(usuarioDTO.getSenha()));
    	Usuario usuario = UsuarioParser.toEntity(usuarioDTO);
    	usuario = usuarioService.salvar(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "atualizarInicias")
    public ResponseEntity<Void> atualizarIniciais(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioParser.toEntity(usuarioDTO);
        usuario = usuarioService.salvar(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "atualizar")
    public ResponseEntity<Void> atualizarUsuario( @RequestBody UsuarioDTO usuarioDTO) {
    	//usuarioDTO.setSenha(usuarioService.encpritografarBcripty(usuarioDTO.getSenha()));
    	
    	usuarioService.atualizar(usuarioDTO);


        return ResponseEntity.noContent().build();
    }

//    @PostMapping(value = "incremento")
//    public ResponseEntity<Void> incrementarUsuario( @RequestBody UsuarioDTO usuarioDTO) {
//        //usuarioDTO.setSenha(usuarioService.encpritografarBcripty(usuarioDTO.getSenha()));
//
//        usuarioService.acrescentarDados(usuarioDTO);
//
//
//        return ResponseEntity.noContent().build();
//    }

    
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
        List<UsuarioDTO> usuariosRecebidos = usuarioService.buscarTodos();

        return ResponseEntity.ok(usuariosRecebidos);
    }

   
    @GetMapping(value = "{email}")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@PathVariable String email) {
        UsuarioDTO usuarioDTO = usuarioService.buscarPorEmail(email);

        return ResponseEntity.ok(usuarioDTO);
    }

    @PostMapping(value = "dados-profissionais")
    public ResponseEntity<UsuarioDTO> inserirDadosProfissionais(@RequestBody DadosProfissionaisDTO dto) {
        Usuario usuario = usuarioService.inserirDadosProfissionais(DadosProfissionaisParser.toEntity(dto));

        return ResponseEntity.ok(UsuarioParser.toDTO(usuario));
    }

    @GetMapping(value = "protected")
    public UsuarioDTO usuarioPorId(Authentication authenticatio) {
        UsuarioDTO usuarioDTO = usuarioService.buscarPorEmail(authenticatio.getName());
        return usuarioDTO;
    }

    @PostMapping(value = "{id}/avaliar")
    public ResponseEntity<Void> adicionarAvaliacao(@PathVariable String id, @RequestBody AvaliacaoCadastroDTO avaliacaoCadastroDTO) {

        usuarioService.adicionarAvaliacao(id, AvaliacaoParser.toEntity(avaliacaoCadastroDTO));


        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/resetPass")
    public void updatePassword(@RequestBody PassWordDTO passWordDTO) {
        usuarioService.updatePassword(passWordDTO);
    }

    @PostMapping(value = "/esqueceuSenha")
    public Usuario esqueceuSenha(@RequestBody EsqueceuSenhaDTO esqueceuSenhaDTO) {

        return  usuarioService.esqueceuSenha
                (esqueceuSenhaDTO);
    }
    @PostMapping(value = "/salvarSenha")
    public ResponseEntity<Void>salvarNovaSenha(@RequestBody EsqueceuSenhaDTO esqueceuSenhaDTO) {
        usuarioService.salvarSenha(esqueceuSenhaDTO);
        return ResponseEntity.noContent().build();
    }
}
