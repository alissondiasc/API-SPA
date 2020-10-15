package com.acj.spa.dto;

import com.acj.spa.entity.Usuario;
import com.acj.spa.enums.StatusAnuncio;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioDTO {

    private String id;
    @NotBlank(message = "${titulo.anuncio.not.blank}")
    @Size(min = 2, max = 50, message = "${titulo.anuncio.size}")
    private String titulo;
    @NonNull
    @NotBlank
    @Size(min = 2, max = 800, message = "${descricao.anuncio.size}")
    private String descricao;
    private StatusAnuncio statusAnuncio;
    @NotNull
    private UsuarioSimplificadoDTO anunciante;
    @NotNull
    private CategoriaDTO categoria;
    private List<Usuario> candidatos;
    private UsuarioSimplificadoDTO profissional;

    private float latitude;
    private float longitude;

}
