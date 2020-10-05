package com.acj.spa.service;

import com.acj.spa.dto.EnderecoDTO;
import com.acj.spa.dto.parser.EnderecoParser;
import com.acj.spa.entity.Endereco;
import com.acj.spa.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;


    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public EnderecoDTO buscarPorId(String id) {
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        return EnderecoParser.toDTO(endereco);
    }

    public List<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

}
