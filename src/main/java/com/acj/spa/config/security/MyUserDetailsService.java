package com.acj.spa.config.security;

import com.acj.spa.entity.Usuario;
import com.acj.spa.repository.UsuarioRepository;
import com.acj.spa.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioService usuarioService;


    @Autowired
    public MyUserDetailsService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)  {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if(Objects.isNull(usuario)){
            log.info("Usuário: " +username +" não encontrado.");
            throw  new RuntimeException("Usuário não encontrado");
        }
        return new UsuarioRepositoryUsuarioDetails(usuario, usuarioService);
    }

    public final static class UsuarioRepositoryUsuarioDetails extends Usuario implements UserDetails {

        private static final long serialVersionUID = 1L;
        transient UsuarioService usuarioService;
        private Usuario usuario;


        @Autowired
        private UsuarioRepositoryUsuarioDetails(Usuario user, UsuarioService usuarioService) {
            super(user);
            this.usuario = user;
            this.usuarioService = usuarioService;
        }
        public Usuario obterUsuarioLogado(){
            return this.usuario;
        }


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            log.info("Buscando permissoes.");
            return usuarioService.finPermissionsByUser(usuario);
        }
        public Usuario obterUsuario(){
            return this.usuario;
        }

        @Override
        public String getUsername() {
            if (Objects.isNull(getEmail())) {
                return getCpf();
            }
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return !getIsDeleted();
        }

        @Override
        public boolean isAccountNonLocked() {
            return !getIsDeleted();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return !getIsDeleted() && !usuarioService.finPermissionsByUser(usuario).isEmpty();
        }

        @Override
        public String getPassword() {
            log.info("Verificando senha.");
            return usuarioService.returnPassword(usuario.getId()).getPassword();
        }
    }
}
