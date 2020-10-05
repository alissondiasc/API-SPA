package com.acj.spa.repository;

import com.acj.spa.entity.Perfil;
import com.acj.spa.enums.TipoPerfil;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PerfilRepository extends MongoRepository<Perfil, String> {

    Perfil findByTipoPerfil(TipoPerfil tipoPerfil);
    Perfil findFirstBy();
    List<Perfil> findByTipoPerfilIn(List<TipoPerfil>tipoPerfils);
}
