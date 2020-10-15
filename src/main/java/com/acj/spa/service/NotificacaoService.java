package com.acj.spa.service;

import com.acj.spa.entity.Anuncio;
import com.acj.spa.entity.NotificacaoEntity;
import com.acj.spa.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {
    @Autowired
    private NotificacaoRepository notificacaoRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public List<NotificacaoEntity> obterNotificacoesNaoLidas(String idUsuario) {
        return notificacaoRepository.findByIdUsuarioAndVistoFalse(idUsuario);
    }

    public void criarNotificacaoDeCandidade(Anuncio anuncio) {
        NotificacaoEntity notificacao =  new NotificacaoEntity
                (anuncio.getAnunciante().getId(),"Você tem um novo candidato!",anuncio.getAnunciante().getNome()+" tem interesse em sua opotunidade");
        notificacaoRepository.save(notificacao);
        simpMessagingTemplate.convertAndSend("/websocket/notificacao/new/"+anuncio.getAnunciante().getId(), obterNotificacoesNaoLidas(anuncio.getAnunciante().getId()).size());
    }

}
