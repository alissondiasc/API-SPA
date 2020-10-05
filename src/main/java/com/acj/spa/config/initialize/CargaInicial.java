package com.acj.spa.config.initialize;

import com.acj.spa.entity.Categoria;
import com.acj.spa.entity.Perfil;
import com.acj.spa.entity.Permission;
import com.acj.spa.entity.Usuario;
import com.acj.spa.enums.Roles;
import com.acj.spa.enums.TipoPerfil;
import com.acj.spa.repository.PerfilRepository;
import com.acj.spa.service.CategoriaService;
import com.acj.spa.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class CargaInicial implements ApplicationListener<ContextRefreshedEvent> {

    private final PerfilRepository perfilRepository;

    private final UsuarioService usuarioService;

    private final CategoriaService categoriaService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (isNull(this.perfilRepository.findFirstBy())) {
            this.createPerfis();
        }
        if (isNull(this.usuarioService.obterUmUsuario())) {

            Usuario usuario = this.usuarioService.salvar(Usuario.builder().nome("Admin").email("admin@email.com").perfils(this.addUserPerfil(Arrays.asList(TipoPerfil.ADMIN))).build());
            this.usuarioService.savePasswordToUserPadrao(usuario);
        }
        initCategorias();
    }

    public void createPerfis() {
        this.perfilRepository.save(new Perfil(
                "Administrador",
                TipoPerfil.ADMIN,
                new HashSet<>(Arrays.asList(new Permission(Roles.ROLER_ADMIN.toString())))));
        this.perfilRepository.save(new Perfil(
                "Cliente",
                TipoPerfil.COMUN,
                new HashSet<>(Arrays.asList(
                        new Permission(Roles.ROLER_CLIENTE.toString())))));
    }

    public List<Perfil> addUserPerfil(List<TipoPerfil> tipoPerfils) {
        return perfilRepository.findByTipoPerfilIn(tipoPerfils);
    }

    private void initCategorias() {
        if (!categoriaService.countCategorias()) {
            List<Categoria> categorias = new ArrayList<>(
                    Arrays.asList(
            new Categoria("Jardinagem"),
            new Categoria("Pintura"),
            new Categoria("Mecânica"),
            new Categoria("Contrução e reforma"),
            new Categoria("Beleza"),
            new Categoria("Comunicação"),
            new Categoria("Educação"),
            new Categoria("Eletrônica"),
            new Categoria("Eventos"),
            new Categoria("Gastronomia"),
            new Categoria("Informática"),
            new Categoria("Limpeza"),
            new Categoria("Manutenções"),
            new Categoria("Móveis"),
            new Categoria("Proteção/Segurança"),
            new Categoria("Saude"),
            new Categoria("Serviçoo Doméstico"),
            new Categoria("Trânsporte"),
            new Categoria("Outros")
            ));
            categoriaService.salvarTodos(categorias);
        }
    }
}
