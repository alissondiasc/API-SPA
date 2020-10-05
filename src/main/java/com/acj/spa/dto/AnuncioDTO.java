package com.acj.spa.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.acj.spa.entities.Usuario;
import com.acj.spa.enums.StatusAnuncio;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioDTO implements Serializable {

    private String id;
    private String titulo;
    private String descricao;
    private UsuarioDTO anunciante;
    private CategoriaDTO categoria;
    private LocalDateTime dataHora;
    private List<Usuario> candidatos;
    private StatusAnuncio statusAnuncio;
    private Usuario profissional;
    private String localidade;
    private String bairro;
}
