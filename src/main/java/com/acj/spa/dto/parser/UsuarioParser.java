package com.acj.spa.dto.parser;

import com.acj.spa.dto.UsuarioDTO;
import com.acj.spa.entities.Usuario;

public class UsuarioParser {

	public static UsuarioDTO toDTO(Usuario usuario) {

		return new UsuarioDTO(usuario.getId(), usuario.getNome(),usuario.getEmail(),usuario.getSenha(), usuario.getDadosProfissionais(), usuario.isAdmin() ,usuario.getEndereco(), usuario.getCpf(),usuario.getRg(),usuario.getOrgaoExpedidor(),usuario.getSexo(),usuario.getEscolaridade(),usuario.getNacionalidade(), usuario.getAvaliacoes(), usuario.getTelefone(),usuario.getCelular(),usuario.getFotoUsuario() );
	}
	
	public static Usuario toEntity(UsuarioDTO usuarioDTO) {
		return new Usuario(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha(), usuarioDTO.getDadosProfissionais(),usuarioDTO.getEndereco(),usuarioDTO.getCpf(),usuarioDTO.getRg(),usuarioDTO.getOrgaoExpedidor(),usuarioDTO.getSexo(),usuarioDTO.getEscolaridade(),usuarioDTO.getNacionalidade(),usuarioDTO.getAvaliacaos(),usuarioDTO.getTelefone(),usuarioDTO.getCelular(),usuarioDTO.getFotoUsuario());
	}

	
}
