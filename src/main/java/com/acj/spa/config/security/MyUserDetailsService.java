package br.com.se.config.security;

import br.com.se.entity.core.usuario.Usuario;
import br.com.se.repository.core.security.PassWordRepository;
import br.com.se.repository.core.usuario.UsuarioRepository;
import br.com.se.service.core.UsuarioService;
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
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private  UsuarioRepository usuarioRepository;
    private  Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PassWordRepository passwordRepository;


    @Autowired
    public MyUserDetailsService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)  {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if(Objects.isNull(usuario)){
            logger.info(String.format("Usuario  %s nao encontrado", username));
            throw  new RuntimeException("Usuário não encontrado");
        }
        return new UsuarioRepositoryUsuarioDetails(usuario, usuarioService, logger);
    }

    private final static class UsuarioRepositoryUsuarioDetails extends Usuario implements UserDetails{

        private static final long serialVersionUID = 1L;

        transient UsuarioService serviceUser;
        private Logger logger;
        private Usuario usuario;


        @Autowired
        private UsuarioRepositoryUsuarioDetails(Usuario user, UsuarioService usuarioService, Logger logger) {
            super(user);
            this.usuario = user;
            this.serviceUser = usuarioService;
            this.logger = logger;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            logger.info("Buscando permissoes...");
            return serviceUser.finPermissionsByUser(usuario);
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
            return !getIsDeleted() && !serviceUser.finPermissionsByUser(usuario).isEmpty();
        }

        @Override
        public String getPassword() {
            return serviceUser.returnPassword(usuario.getId()).getPassword();
        }
    }
}
