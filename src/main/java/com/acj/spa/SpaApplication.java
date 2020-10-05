package com.acj.spa;

import com.acj.spa.entities.Categoria;
import com.acj.spa.entities.Usuario;
import com.acj.spa.enums.Perfil;
import com.acj.spa.services.CategoriaService;
import com.acj.spa.services.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@SpringBootApplication
public class SpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaApplication.class, args);
	}

    @Bean
    CommandLineRunner init(UsuarioService usuarioService, CategoriaService categoriaService) {
        return args -> {
            initUsers(usuarioService);
            initCategorias(categoriaService);
        };
    }


    private void initUsers(UsuarioService usuarioService) {
        if (usuarioService.findByEmail("admin@email.com") == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Usuario admin = new Usuario();
            String senha = "Admin123";

            admin.setNome("Admin");
            admin.setEmail("admin@email.com");
            admin.setSenha(passwordEncoder.encode(senha));
            admin.setPerfil(Perfil.ROLE_ADMIN);

            usuarioService.salvar(admin);
        }
    }

    private void initCategorias(CategoriaService categoriaService) {
	    if(!categoriaService.countCategorias()) {
            Categoria cat1 = new Categoria(null, "Jardinagem");
            Categoria cat2 = new Categoria(null, "Pintura");
            Categoria cat3 = new Categoria(null, "Mecânica");
            Categoria cat4 = new Categoria(null, "Contrução e reforma");
            Categoria cat5 = new Categoria(null, "Beleza");
            Categoria cat6 = new Categoria(null, "Comunicação");
            Categoria cat7 = new Categoria(null, "Educação");
            Categoria cat8 = new Categoria(null, "Eletrônica");
            Categoria cat9 = new Categoria(null, "Eventos");
            Categoria cat10 = new Categoria(null, "Gastronomia");
            Categoria cat11 = new Categoria(null, "Informática");
            Categoria cat12 = new Categoria(null, "Limpeza");
            Categoria cat13 = new Categoria(null, "Manutenções");
            Categoria cat14 = new Categoria(null, "Móveis");
            Categoria cat15 = new Categoria(null, "Proteção/Segurança");
            Categoria cat16 = new Categoria(null, "Saude");
            Categoria cat17 = new Categoria(null, "Serviçoo Doméstico");
            Categoria cat18 = new Categoria(null, "Trânsporte");
            Categoria cat19 = new Categoria(null, "Outros");

            if (categoriaService.buscarPorNome(cat1.getNome()) == null) {
                categoriaService.salvar(cat1);
            }
            if (categoriaService.buscarPorNome(cat2.getNome()) == null) {
                categoriaService.salvar(cat2);
            }
            if (categoriaService.buscarPorNome(cat3.getNome()) == null) {
                categoriaService.salvar(cat3);
            }
            if (categoriaService.buscarPorNome(cat4.getNome()) == null) {
                categoriaService.salvar(cat4);
            }
            if (categoriaService.buscarPorNome(cat5.getNome()) == null) {
                categoriaService.salvar(cat5);
            }
            if (categoriaService.buscarPorNome(cat6.getNome()) == null) {
                categoriaService.salvar(cat6);
            }
            if (categoriaService.buscarPorNome(cat7.getNome()) == null) {
                categoriaService.salvar(cat7);
            }
            if (categoriaService.buscarPorNome(cat8.getNome()) == null) {
                categoriaService.salvar(cat8);
            }
            if (categoriaService.buscarPorNome(cat9.getNome()) == null) {
                categoriaService.salvar(cat9);
            }
            if (categoriaService.buscarPorNome(cat10.getNome()) == null) {
                categoriaService.salvar(cat10);
            }
            if (categoriaService.buscarPorNome(cat11.getNome()) == null) {
                categoriaService.salvar(cat11);
            }
            if (categoriaService.buscarPorNome(cat12.getNome()) == null) {
                categoriaService.salvar(cat12);
            }
            if (categoriaService.buscarPorNome(cat13.getNome()) == null) {
                categoriaService.salvar(cat13);
            }
            if (categoriaService.buscarPorNome(cat14.getNome()) == null) {
                categoriaService.salvar(cat14);
            }
            if (categoriaService.buscarPorNome(cat15.getNome()) == null) {
                categoriaService.salvar(cat15);
            }
            if (categoriaService.buscarPorNome(cat16.getNome()) == null) {
                categoriaService.salvar(cat16);
            }
            if (categoriaService.buscarPorNome(cat17.getNome()) == null) {
                categoriaService.salvar(cat17);
            }
            if (categoriaService.buscarPorNome(cat18.getNome()) == null) {
                categoriaService.salvar(cat18);
            }
            if (categoriaService.buscarPorNome(cat19.getNome()) == null) {
                categoriaService.salvar(cat19);
            }
        }
    }
}
