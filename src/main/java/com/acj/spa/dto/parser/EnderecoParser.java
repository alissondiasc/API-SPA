package com.acj.spa.dto.parser;

import com.acj.spa.dto.EnderecoDTO;
import com.acj.spa.entities.Endereco;

public class EnderecoParser {

	public static EnderecoDTO toDTO(Endereco endereco) {
		return new EnderecoDTO(endereco.getCep(), endereco.getBairro(), endereco.getLocalidade(),
				endereco.getLogradouro(), endereco.getRua(), endereco.getIbge(), endereco.getUf());
	}

	public static Endereco toEntity(EnderecoDTO enderecoDTO) {
		return new Endereco(enderecoDTO.getCep(), enderecoDTO.getBairro(), enderecoDTO.getCidade(),
				enderecoDTO.getEstado(), enderecoDTO.getRua(), enderecoDTO.getIbge(), enderecoDTO.getUf());
	}

}
