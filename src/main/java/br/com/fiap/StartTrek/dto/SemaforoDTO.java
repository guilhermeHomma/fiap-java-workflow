package br.com.fiap.StartTrek.dto;

import jakarta.validation.constraints.*;

import java.util.Date;

public record SemaforoDTO(
        @NotNull(message = "O ID do semáforo é obrigatório.")
        Long idSemaforo,

        @NotBlank(message = "A localização é obrigatória.")
        @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres.")
        String localizacao,

        @NotBlank(message = "O status atual é obrigatório.")
        @Size(max = 20, message = "O status atual deve ter no máximo 20 caracteres.")
        String statusAtual,

        @NotNull(message = "A última atualização é obrigatória.")
        Date ultimaAtualizacao
) {
}
