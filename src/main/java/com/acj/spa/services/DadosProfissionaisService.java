package com.acj.spa.services;

import com.acj.spa.entities.DadosProfissionais;
import com.acj.spa.repository.DadosProfissionaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DadosProfissionaisService {

    @Autowired
    private DadosProfissionaisRepository dadosProfissionaisRepository;

    public DadosProfissionais salvar(DadosProfissionais dadosProfissionais) {
        return dadosProfissionaisRepository.save(dadosProfissionais);
    }
}
