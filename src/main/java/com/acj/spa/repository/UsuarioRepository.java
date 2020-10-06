package com.acj.spa.repository;

import com.acj.spa.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	Usuario findByEmail(String email);
	Usuario findByCpfOrEmail(String cpf);

    Usuario findFirstBy();
}
