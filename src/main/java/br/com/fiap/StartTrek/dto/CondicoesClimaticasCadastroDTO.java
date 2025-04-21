package br.com.fiap.StartTrek.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

public record CondicoesClimaticasCadastroDTO(
        Long idCondicao,

        @NotBlank(message = "O tipo de condição é obrigatório.")
        @Size(max = 50, message = "O tipo de condição deve ter no máximo 50 caracteres.")
        String tipoCondicao,

        @Size(max = 50, message = "A intensidade deve ter no máximo 50 caracteres.")
        String intensidade,

        @NotNull(message = "O horário de registro é obrigatório.")
        Date horarioRegistro
) {
}
