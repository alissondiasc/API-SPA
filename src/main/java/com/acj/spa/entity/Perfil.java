package com.acj.spa.entity;

import com.acj.spa.enums.TipoPerfil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.Set;

@Document
@Getter
@Setter
@Builder
public class Perfil extends GenericEntity implements Comparable<Perfil> {

    private String nome;

    private TipoPerfil tipoPerfil;

    private Set<Permission> permissions;

    public Perfil(String nome, TipoPerfil tipoPerfil, Set<Permission> permissions) {
        this.nome = nome;
        this.tipoPerfil = tipoPerfil;
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Perfil)) return false;
        if (!super.equals(o)) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(nome, perfil.nome) &&
                tipoPerfil == perfil.tipoPerfil;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nome, tipoPerfil);
    }

    @Override
    public int compareTo(Perfil perfil) {
        return getTipoPerfil().compareTo(perfil.getTipoPerfil());
    }


}
