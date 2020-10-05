package com.acj.spa.repository;

import com.acj.spa.entity.Password;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PassWordRepository extends MongoRepository<Password, String> {
    Page<Password> findByIdUser(String id, Pageable pages);

    List<Password> findByIdUser(String id);

    void deleteByIdUser(String id);
}
