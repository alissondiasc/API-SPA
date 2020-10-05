package com.acj.spa.repository;

import com.acj.spa.entities.DadosProfissionais;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosProfissionaisRepository extends MongoRepository<DadosProfissionais, String> {
}
