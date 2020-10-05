package com.acj.spa.repository;

import com.acj.spa.entities.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, String> {

    Categoria findByNome(String nome);
    int countByIdNotNull();
}
