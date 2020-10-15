package com.acj.spa.dto;

import com.acj.spa.enums.StatusAnuncio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioRegistrosDTO {
    private String id;
    private String titulo;
    private String descricao;
    private CategoriaAnuncioDTO categoria;
    private List<UsuarioSimplificadoDTO> candidatos;
    private UsuarioSimplificadoDTO anunciante;
    private StatusAnuncio status;
    private UsuarioSimplificadoDTO profissional;
    private UsuarioSimplificadoDTO usuario;
    private String localidade;
    private String bairro;

}
