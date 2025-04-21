package br.com.fiap.StartTrek.dto;

import br.com.fiap.StartTrek.model.Usuario;
import br.com.fiap.StartTrek.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioExibicaoDTO(
        Long usuarioId,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email deve ser válido")
        String email,

        UsuarioRole role // Inclui role para exibição
) {
    // Construtor para criar um UsuarioExibicaoDTO a partir de um Usuario existente
    public UsuarioExibicaoDTO(Usuario usuario) {
        this(usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole());
    }
}
