package br.com.fiap.StartTrek.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long usuarioId;
    private String nome;
    private String email;
    private String senha; // Para a criação de novos usuários
    private LocalDate dtCriacao; // Para a data de criação
    private String role; // Para o papel do usuário (por exemplo, ADMIN ou USER)
}
