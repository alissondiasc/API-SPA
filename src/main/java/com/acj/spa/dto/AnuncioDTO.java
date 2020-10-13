package com.acj.spa.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.acj.spa.entity.Usuario;
import com.acj.spa.enums.StatusAnuncio;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioDTO implements Serializable {
    @NonNull
    @NotEmpty
    private String id;
    @NonNull
    @NotEmpty
    @Size(min = 2, max = 50, message = "teste")
    private String titulo;
    @NonNull
    @NotEmpty
    @Size(min = 1, message = "teste")
    private String descricao;
    @NonNull
    private UsuarioDTO anunciante;
    @NonNull
    private CategoriaDTO categoria;
    private LocalDateTime dataHora;
    private List<Usuario> candidatos;
    private StatusAnuncio statusAnuncio;
    private Usuario profissional;
    @NonNull
    @NotEmpty
    private String localidade;
    @NonNull
    @NotEmpty
    private String bairro;
}
