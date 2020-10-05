package br.com.se.config.initialize;

import br.com.se.entity.core.domains.GeneroDomain;
import br.com.se.entity.core.permissions.Permission;
import br.com.se.entity.core.permissions.Roles;
import br.com.se.entity.core.permissions.TipoPerfil;
import br.com.se.entity.core.usuario.Perfil;
import br.com.se.entity.core.usuario.Usuario;
import br.com.se.pathBase.administracao.AdministracaoPathBase;
import br.com.se.pathBase.client.ClientPathBase;
import br.com.se.repository.core.domains.GeneroRepository;
import br.com.se.repository.core.permissoes.PerfilRepository;
import br.com.se.repository.core.usuario.UsuarioRepository;
import br.com.se.service.core.UsuarioService;
import br.com.se.utils.enuns.GeneroEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CargaInicial implements ApplicationListener<ContextRefreshedEvent> {


    private final UsuarioRepository usuarioRepository;

    private final PerfilRepository perfilRepository;

    private final UsuarioService usuarioService;

    private final GeneroRepository generoRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        if(Objects.isNull(this.perfilRepository.findFirstBy())){
            this.createPerfis();
        }
        if(this.usuarioRepository.findAll().isEmpty()){
            Usuario usuario = this.usuarioRepository.save(new Usuario("Alisson","alisson@email.com",this.addUserPerfil(Arrays.asList(TipoPerfil.ADMIN))));
            this.usuarioService.savePasswordToUserPadrao(usuario);
        }
        if(Objects.isNull(this.generoRepository.findFirstBy())){
            this.createDomains();
        }
    }

    public void createPerfis(){
        this.perfilRepository.save(new Perfil(
                "Administrador",
                TipoPerfil.ADMIN,
                new HashSet<Permission>(Arrays.asList(new Permission(Roles.ROLER_ADMIN.toString())))));
        this.perfilRepository.save(new Perfil(
                "Cliente",
                TipoPerfil.COMUN,
                new HashSet<Permission>(Arrays.asList(
                        new Permission(Roles.ROLER_CLIENTE.toString())))));
    }

    public void createDomains(){
        List<GeneroDomain>generoDomains = new ArrayList<GeneroDomain>(Arrays.asList(
                new GeneroDomain(GeneroEnum.FEMININO.toString()),
                new GeneroDomain(GeneroEnum.IGNORADO.toString()),
                new GeneroDomain(GeneroEnum.MASCULINO.toString())));
        this.generoRepository.saveAll(generoDomains);
    }
    public List<Perfil> addUserPerfil( List<TipoPerfil> tipoPerfils){
       return perfilRepository.findByTipoPerfilIn(tipoPerfils);
    }
}
