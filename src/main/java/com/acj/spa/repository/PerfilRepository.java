package br.com.se.repository.core.permissoes;

import br.com.se.entity.core.permissions.TipoPerfil;
import br.com.se.entity.core.usuario.Perfil;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PerfilRepository extends MongoRepository<Perfil, String> {

    Perfil findByTipoPerfil(TipoPerfil tipoPerfil);
    Perfil findFirstBy();
    List<Perfil> findByTipoPerfilIn(List<TipoPerfil>tipoPerfils);
}
