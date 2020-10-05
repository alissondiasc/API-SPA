package br.com.se.entity.core.usuario;

import br.com.se.entity.core.generic.GenericEntity;
import br.com.se.entity.core.permissions.TipoPerfil;

import br.com.se.entity.core.permissions.Permission;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.Set;

@Document
@Getter
@Setter
public class Perfil extends GenericEntity implements Comparable<Perfil>{

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
