package com.acj.spa.service;

import com.acj.spa.dto.*;
import com.acj.spa.dto.parser.AnuncioParser;
import com.acj.spa.entity.Anuncio;
import com.acj.spa.entity.QAnuncio;
import com.acj.spa.entity.Usuario;
import com.acj.spa.enums.StatusAnuncio;
import com.acj.spa.repository.AnuncioRepository;
import com.acj.spa.service.exception.ObjectNotFoundException;
import com.acj.spa.util.CoreUtils;
import com.acj.spa.util.ObjectMapperUtils;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service

public class AnuncioService {
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private NotificacaoService notificacaoService;

    public String cadastrar(AnuncioDTO anuncioDTO) {
        try {
            anuncioDTO.setCategoria(CategoriaDTO.builder().id(anuncioDTO.getCategoria().getId()).build());
            LocalDateTime agora = LocalDateTime.now();
            agora.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            anuncioDTO.setDataHora(agora);
            Anuncio anuncio = ObjectMapperUtils.map(anuncioDTO, Anuncio.class);
            anuncio.setUsuario(usuarioService.obterUsuarioLogado());
            anuncioRepository.save(anuncio);
            return "Anuncio cadastrado com sucesso.";
        }catch (Exception  e){
            return e.getMessage();
        }


    }

    public List<AnuncioRegistrosDTO> listarMeusAnuncios() {
        List<Anuncio> anuncios = anuncioRepository.findByUsuarioAndIsDeletedFalseOrderByDataHoraDesc(usuarioService.obterUsuarioLogado());
        return ObjectMapperUtils.mapAll(anuncios, AnuncioRegistrosDTO.class);
    }

    public ListPageDTO filtrarAnuncios(FiltroAnunciosDTO filtroAnunciosDTO) {
        final QAnuncio qAnuncio = new QAnuncio("anuncio");
        Usuario usuarioLogado = usuarioService.obterUsuarioLogado();
        Pageable pageable = PageRequest.of(filtroAnunciosDTO.getPage(), filtroAnunciosDTO.getCont(), filtroAnunciosDTO.getOrderBy(), "dataHora");
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qAnuncio.isDeleted.eq(false));
        if(Objects.nonNull(usuarioLogado)){
            builder.and(qAnuncio.usuario.id.notIn(usuarioLogado.getId()));
        }

        if (!StringUtils.isEmpty(filtroAnunciosDTO.getDescricao())) {
            builder
                    .and(qAnuncio.titulo.matches(CoreUtils.retornarPrimeiroCampoRegex(filtroAnunciosDTO.getDescricao())).and(qAnuncio.titulo.matches(CoreUtils.retornarRegexParametroPesquisa(filtroAnunciosDTO.getDescricao()))))
                    .or(qAnuncio.descricao.matches(CoreUtils.retornarPrimeiroCampoRegex(filtroAnunciosDTO.getDescricao())).and(qAnuncio.descricao.matches(CoreUtils.retornarRegexParametroPesquisa(filtroAnunciosDTO.getDescricao()))));

        }
        if (Objects.nonNull(filtroAnunciosDTO.getStatusAnuncio())) {
            builder.and(qAnuncio.status.eq(filtroAnunciosDTO.getStatusAnuncio())).or(qAnuncio.status.eq(StatusAnuncio.FINALIZADO));
        }
        if (Objects.nonNull(filtroAnunciosDTO.getIdProfissional())) {
            builder.and(qAnuncio.profissional.id.eq(filtroAnunciosDTO.getIdProfissional()));
        }
        if (Objects.nonNull(filtroAnunciosDTO.getCategoriaDTO())) {
            builder.and(qAnuncio.categoria.id.eq(filtroAnunciosDTO.getCategoriaDTO().getId()));
        }
        if (Objects.nonNull(filtroAnunciosDTO.getBairro())) {
            builder.and(qAnuncio.bairro.eq(filtroAnunciosDTO.getBairro()));
        }
        if (Objects.nonNull(filtroAnunciosDTO.getCategoriaDTO())) {
            builder.and(qAnuncio.localidade.eq(filtroAnunciosDTO.getCidade()));
        }
        Page request = anuncioRepository.findAll(builder, pageable);

        return new ListPageDTO(ObjectMapperUtils.mapAll(request.getContent(), AnuncioRegistrosDTO.class), request.getTotalElements());
    }

    public Anuncio buscarPorId(String id) {
        return anuncioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Anúncio não encontrado"));
    }

    public List<UsuarioSimplificadoDTO> obterCandidatosDoAnuncio(String id) {
        Anuncio anuncio = anuncioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Anúncio não encontrado"));
        return ObjectMapperUtils.mapAll(anuncio.getCandidatos(), UsuarioSimplificadoDTO.class);
    }


    public String candidatar(String anuncioId) {
        Anuncio anuncio = buscarPorId(anuncioId);
        Usuario usuario = usuarioService.obterUsuarioLogado();

        if (this.candidaturaValida(usuario, anuncio)) {
            if (Objects.isNull(anuncio.getCandidatos())) {
                anuncio.setCandidatos(Arrays.asList(usuario));
            } else {
                anuncio.getCandidatos().add(usuario);
            }
            anuncioRepository.save(anuncio);
            this.notificacaoService.criarNotificacaoDeCandidade(anuncio);
            return "Candidatura realizada com sucesso.";
        } else {
            throw new DataIntegrityViolationException("Você já se candidatou.");
        }
    }

    public void deletarMeuAnuncio(String anuncioId) {
        this.anuncioRepository.deleteById(anuncioId);
    }


    public boolean candidaturaValida(Usuario usuario, Anuncio anuncio) {
        if (usuario.getId().equals(anuncio.getUsuario().getId())) {
            return Boolean.FALSE;
        } else {
            if (anuncio.getCandidatos().stream().filter(user -> user.getId().equals(usuario.getId())).collect(Collectors.toList()).isEmpty()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public void escolherCandidato(String idAnuncio, String idUsuario) {
        Anuncio anuncio = buscarPorId(idAnuncio);
        Usuario candidato = usuarioService.buscarPorId(idUsuario);
        Usuario usuario = usuarioService.obterUsuarioLogado();

        if (anuncio.getUsuario().equals(usuario)) {
            anuncio.setProfissional(candidato);
            anuncio.setStatus(StatusAnuncio.EM_ANDAMENTO);
            anuncioRepository.save(anuncio);
        }
    }

    public void finalizarServico(String idAnuncio) {
        Anuncio anuncio = buscarPorId(idAnuncio);
        anuncio.setStatus(StatusAnuncio.FINALIZADO);
        anuncioRepository.save(anuncio);
        anuncio.getProfissional().getDadosProfissionais().setQtdServicos(anuncio.getProfissional().getDadosProfissionais().getQtdServicos() != null ? anuncio.getProfissional().getDadosProfissionais().getQtdServicos() + 1 : 1);
        usuarioService.acrescentarDados(anuncio.getProfissional());

    }

    public void encerrarServico(String idAnuncio) {
        Anuncio anuncio = buscarPorId(idAnuncio);
        anuncio.setStatus(StatusAnuncio.ANUNCIOFINALIZADO);
        anuncio.setDeleted(Boolean.TRUE);
        anuncioRepository.save(anuncio);

    }
}
