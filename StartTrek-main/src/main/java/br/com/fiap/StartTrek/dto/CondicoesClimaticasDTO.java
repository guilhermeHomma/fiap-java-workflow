package br.com.fiap.StartTrek.dto;

import jakarta.validation.constraints.*;

import java.util.Date;

public record CondicoesClimaticasDTO(
        @NotNull(message = "O ID da condição é obrigatório.")
        Long idCondicao,

        @NotBlank(message = "O tipo de condição é obrigatório.")
        @Size(max = 50, message = "O tipo de condição deve ter no máximo 50 caracteres.")
        String tipoCondicao,

        @Size(max = 50, message = "A intensidade deve ter no máximo 50 caracteres.")
        String intensidade,

        @NotNull(message = "O horário de registro é obrigatório.")
        @PastOrPresent(message = "O horário de registro não pode ser futuro.")
        Date horarioRegistro
) {
}
