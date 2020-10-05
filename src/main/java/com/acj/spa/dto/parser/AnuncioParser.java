package com.acj.spa.dto.parser;

import com.acj.spa.dto.AnuncioDTO;
import com.acj.spa.dto.CategoriaDTO;
import com.acj.spa.dto.UsuarioDTO;
import com.acj.spa.entities.Anuncio;
import com.acj.spa.entities.Categoria;
import com.acj.spa.entities.Usuario;

public class AnuncioParser {

    public static AnuncioDTO toDTO(Anuncio anuncio) {
        UsuarioDTO usuarioDTO = UsuarioParser.toDTO(anuncio.getUsuario());
        CategoriaDTO categoriaDTO = CategoriaParser.toDTO(anuncio.getCategoria());

        return new AnuncioDTO(anuncio.getId(), anuncio.getTitulo(), anuncio.getDescricao(), usuarioDTO, categoriaDTO, anuncio.getDataHora(),anuncio.getCandidatos(), anuncio.getStatus(),anuncio.getProfissional(),anuncio.getLocalidade(),anuncio.getBairro());
    }

    public static Anuncio toEntity(AnuncioDTO anuncioDTO) {
        Usuario usuario = UsuarioParser.toEntity(anuncioDTO.getAnunciante());
        Categoria categoria = CategoriaParser.toEntity(anuncioDTO.getCategoria());

        return new Anuncio(anuncioDTO.getId(), anuncioDTO.getTitulo(), anuncioDTO.getDescricao(), categoria, usuario, anuncioDTO.getDataHora(),anuncioDTO.getCandidatos(), anuncioDTO.getProfissional(),anuncioDTO.getLocalidade(),anuncioDTO
        .getBairro());
    }
}
