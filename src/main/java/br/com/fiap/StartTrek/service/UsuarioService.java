package br.com.fiap.StartTrek.service;

import br.com.fiap.StartTrek.dto.UsuarioCadastroDTO;
import br.com.fiap.StartTrek.dto.UsuarioExibicaoDTO;
import br.com.fiap.StartTrek.model.Usuario;
import br.com.fiap.StartTrek.model.UsuarioRole;
import br.com.fiap.StartTrek.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(@Lazy UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        // Copia as propriedades do DTO para a entidade, exceto a senha e os campos gerados automaticamente
        BeanUtils.copyProperties(usuarioDTO, usuario);

        // Define a senha criptografada
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));

        // Define valores padrão para role e data de criação, caso estejam ausentes
        usuario.setRole(usuarioDTO.role() != null ? usuarioDTO.role() : UsuarioRole.USER);
        usuario.setDtCriacao(LocalDate.now());

        // Salva o usuário e retorna o DTO de exibição
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDTO(usuarioSalvo);
    }
}
