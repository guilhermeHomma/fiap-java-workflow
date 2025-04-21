package br.com.fiap.StartTrek.dto;

import jakarta.validation.constraints.*;

import java.util.Date;

public record FluxoTrafegoCadastroDTO(
        Long idFluxo,

        @NotNull(message = "O ID do semáforo é obrigatório.")
        Long idSemaforo,

        @NotNull(message = "A contagem de veículos é obrigatória.")
        @Min(value = 0, message = "A contagem de veículos não pode ser negativa.")
        Integer contagemVeiculos,

        @NotNull(message = "O horário de registro é obrigatório.")
        Date horarioRegistro
) {
}
