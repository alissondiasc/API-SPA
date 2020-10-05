package com.acj.spa.repository;

import com.acj.spa.entity.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository  extends MongoRepository<Endereco, String> {
}
