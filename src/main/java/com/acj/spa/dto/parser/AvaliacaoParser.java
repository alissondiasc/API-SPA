package com.acj.spa.dto.parser;

import com.acj.spa.dto.AvaliacaoCadastroDTO;
import com.acj.spa.entities.Avaliacao;

public class AvaliacaoParser {

    public static Avaliacao toEntity(AvaliacaoCadastroDTO dto) {
        return new Avaliacao(dto.getId(),dto.getNota(),dto.getComentario(),dto.getUsuario(),dto.getAnuncio());
    }
}
