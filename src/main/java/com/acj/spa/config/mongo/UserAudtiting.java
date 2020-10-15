package com.acj.spa.config.mongo;

import com.acj.spa.config.security.MyUserDetailsService;
import com.acj.spa.entity.Usuario;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.acj.spa.util.template.MessageLoader.getMessage;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class UserAudtiting implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isNull(authentication) || !authentication.isAuthenticated())
            return Optional.of(getMessage("config.auditing.mongo.user.not.found"));
        Usuario usuario = ((MyUserDetailsService.UsuarioRepositoryUsuarioDetails) authentication.getPrincipal()).obterUsuarioLogado();
        if (nonNull(usuario))
            return Optional.of(usuario.getEmail());

        return Optional.of(getMessage("config.auditing.mongo.user.not.found"));
    }
}
