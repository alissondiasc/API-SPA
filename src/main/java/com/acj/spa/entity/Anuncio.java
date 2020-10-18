package com.acj.spa.entity;

import com.acj.spa.enums.StatusAnuncio;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="AnuncioModel", description="Modelo de amostra para a documentação")
public class Anuncio extends GenericEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "${titulo.anuncio.not.blank}")
    private String titulo;
    private String descricao;
    private StatusAnuncio status = StatusAnuncio.NOVO;
    @NotBlank(message = "${localidade.anuncio.not.blank}")
    private String localidade;
    @NotBlank(message = "${bairro.anuncio.not.blank}")
    private String bairro;
    @NotNull(message = "${categoria.anuncio.not.null}")
    @DBRef
    private Categoria categoria;

    @DBRef
    @NotNull(message = "${usuario.anuncio.not.null}")
    private Usuario anunciante;

    @DBRef
    private Usuario profissionalContratado;

    @DBRef
    private List<Usuario> candidatos;


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            Usuario qualquer = (Usuario) obj;
            return this.id.equals(qualquer.getId());
        }else {
            return false;
        }
    }
}
