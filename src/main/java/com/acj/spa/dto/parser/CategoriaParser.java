package com.acj.spa.dto.parser;

import com.acj.spa.dto.CategoriaDTO;
import com.acj.spa.entities.Categoria;

public class CategoriaParser {

	public static CategoriaDTO toDTO(Categoria categoria) {
		return new CategoriaDTO(categoria.getId(), categoria.getNome());
	}

	public static Categoria toEntity(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
}
