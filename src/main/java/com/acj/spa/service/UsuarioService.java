package com.acj.spa.service;

import com.acj.spa.dto.EsqueceuSenhaDTO;
import com.acj.spa.dto.PassWordDTO;
import com.acj.spa.dto.UsuarioDTO;
import com.acj.spa.dto.parser.UsuarioParser;
import com.acj.spa.entity.Avaliacao;
import com.acj.spa.entity.DadosProfissionais;
import com.acj.spa.entity.Usuario;

import com.acj.spa.repository.AnuncioRepository;
import com.acj.spa.repository.UsuarioRepository;
import com.acj.spa.config.security.UsuarioSecurity;
import com.acj.spa.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private AnuncioService anuncioService ;

    @Autowired
    private DadosProfissionaisService dadosProfissionaisService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario obterUsuarioLogado() {
        UsuarioSecurity usuarioSecurity = (UsuarioSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return usuarioRepository.findByEmail(usuarioSecurity.getUsername());
    }

    public Usuario salvar(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    public UsuarioDTO atualizar(UsuarioDTO usuarioDTO) {
    	Usuario usuario = this.obterUsuarioLogado();
    	if(!Objects.isNull(usuarioDTO.getNome())) {
            usuario.setNome(usuarioDTO.getNome());
        }
    	if(usuarioDTO.getCpf() !=null)
    	usuario.setCpf(usuarioDTO.getCpf());
    	if(usuarioDTO.getDadosProfissionais() !=null)
    	usuario.setDadosProfissionais(usuarioDTO.getDadosProfissionais());
    	if(usuarioDTO.getEndereco() !=null)
    	usuario.setEndereco(usuarioDTO.getEndereco());
    	if (usuarioDTO.getOrgaoExpedidor() !=null)
    	usuario.setOrgaoExpedidor(usuarioDTO.getOrgaoExpedidor());
    	if (usuarioDTO.getRg() !=null)
    	usuario.setRg(usuarioDTO.getRg());
    	if (usuarioDTO.getEscolaridade() !=null)
    	usuario.setEscolaridade(usuarioDTO.getEscolaridade());
    	if (usuarioDTO.getSexo() !=null)
    	usuario.setSexo(usuarioDTO.getSexo());

        if (usuarioDTO.getTelefone() !=null)
            usuario.setTelefone(usuarioDTO.getTelefone());

        if (usuarioDTO.getCelular() !=null)
            usuario.setCelular(usuarioDTO.getCelular());

            usuario.setFotoUsuario(usuarioDTO.getFotoUsuario());

        return UsuarioParser.toDTO(usuarioRepository.save(usuario));
    }
    public Usuario  acrescentarDados(Usuario usuarioDTO) {
        Usuario usuarioDTO1 = usuarioDTO;
        if(!Objects.isNull(usuarioDTO.getNome())) {
            usuarioDTO1.setNome(usuarioDTO.getNome());
        }
        if(usuarioDTO.getCpf() !=null)
            usuarioDTO1.setCpf(usuarioDTO.getCpf());
        if(usuarioDTO.getDadosProfissionais() !=null)
            usuarioDTO1.setDadosProfissionais(usuarioDTO.getDadosProfissionais());
        if(usuarioDTO.getEndereco() !=null)
            usuarioDTO1.setEndereco(usuarioDTO.getEndereco());
        if (usuarioDTO.getOrgaoExpedidor() !=null)
            usuarioDTO1.setOrgaoExpedidor(usuarioDTO.getOrgaoExpedidor());
        if (usuarioDTO.getRg() !=null)
            usuarioDTO1.setRg(usuarioDTO.getRg());
        if (usuarioDTO.getEscolaridade() !=null)
            usuarioDTO1.setEscolaridade(usuarioDTO.getEscolaridade());
        if (usuarioDTO.getSexo() !=null)
            usuarioDTO1.setSexo(usuarioDTO.getSexo());

        return usuarioRepository.save(usuarioDTO);
    }
    public void updatePassword(PassWordDTO passWordDTO) {
        Usuario usuario = obterUsuarioLogado();
        if (passwordEncoder.matches(passWordDTO.getAntiga(),usuario.getSenha())) {
            usuario.setSenha(encpritografarBcripty(passWordDTO.getNova()));
            usuarioRepository.save(usuario);
        } else {
            throw new SecurityException("Senhas não conferem senha usuario:" +usuario.getSenha()+"senha antiga:" + passWordDTO.getAntiga());
        }
    }

    public List<UsuarioDTO> buscarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioParser::toDTO).collect(Collectors.toList());
    }

    public Usuario buscarPorId(String id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public UsuarioDTO buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return UsuarioParser.toDTO(usuario);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

	//METODO DE ENCRIPTO PARA SENHAS
	public String encpritografarBcripty(String senha) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(senha);
	}

	public Usuario inserirDadosProfissionais(DadosProfissionais dadosProfissionais) {
        Usuario usuario = obterUsuarioLogado();
        usuario.setDadosProfissionais(dadosProfissionaisService.salvar(dadosProfissionais));

        return usuarioRepository.save(usuario);
    }

    public void adicionarAvaliacao(String id, Avaliacao avaliacao) {

        Usuario profissional = buscarPorId(avaliacao.getAnuncio().getProfissional().getId());
        Usuario avaliador = obterUsuarioLogado();
        avaliacao.setUsuario(avaliador);
        profissional.getAvaliacoes().add(avaliacao);
//        Anuncio anuncio = anuncioService.buscarPorId(avaliacao.getAnuncio().getId());
//        anuncio.setStatus(StatusAnuncio.ENCERRADO);
//        anuncioRepository.save(anuncio);

        usuarioRepository.save(profissional);
    }

    public Usuario esqueceuSenha(EsqueceuSenhaDTO esqueceuSenhaDTO){
        Usuario usuario = usuarioRepository.findByEmail(esqueceuSenhaDTO.getEmail());
        Usuario usuario2 = new Usuario();
        if(Objects.nonNull(usuario)){

            if(usuario.getCpf().equals(esqueceuSenhaDTO.getCpf())){
                usuario2 = usuario;
            }else{
                throw new SecurityException("CPF incompatível" );
            }

        }else{
            throw new SecurityException("Email não cadastrado" );
        }
        return usuario2;
    }
    public Usuario  salvarSenha(EsqueceuSenhaDTO esqueceuSenhaDTO) {
        Usuario usuario = usuarioRepository.findByEmail(esqueceuSenhaDTO.getEmail());

        if(Objects.nonNull(usuario)){
            usuario.setSenha(encpritografarBcripty(esqueceuSenhaDTO.getSenha()));
        }else{
            throw new SecurityException("Email não cadastrado" );
        }
        return usuarioRepository.save(usuario);
    }



}
