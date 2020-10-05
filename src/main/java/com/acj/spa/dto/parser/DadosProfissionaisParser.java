package com.acj.spa.dto.parser;

import com.acj.spa.dto.DadosProfissionaisDTO;
import com.acj.spa.entities.Categoria;
import com.acj.spa.entities.DadosProfissionais;

import java.util.ArrayList;
import java.util.List;

public class DadosProfissionaisParser {

	public static DadosProfissionais toEntity(DadosProfissionaisDTO dto) {
		Categoria profissaoPrincipal = CategoriaParser.toEntity(dto.getProfissaoPrincipal());
        List<Categoria> outrasProfissoes = new ArrayList<>();

		DadosProfissionais dadosProfissionais = new DadosProfissionais(dto.getResumo(), dto.getQtdServicos(), profissaoPrincipal);

		dto.getOutrasProfissoes().forEach(c -> outrasProfissoes.add(CategoriaParser.toEntity(c)));
		dadosProfissionais.setOutrasProfissoes(outrasProfissoes);

		return dadosProfissionais;
	}
}
