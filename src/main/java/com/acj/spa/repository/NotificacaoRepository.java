package com.acj.spa.repository;

import com.acj.spa.entities.NotificacaoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificacaoRepository extends MongoRepository<NotificacaoEntity, String> {
    List<NotificacaoEntity> findByIdUsuarioAndVistoFalse(String idUsuario);
}
