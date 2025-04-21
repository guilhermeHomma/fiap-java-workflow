package br.com.fiap.StartTrek.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Date;

public record SemaforoAtualizacaoDTO(
        @NotBlank(message = "A localização é obrigatória.")
        @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres.")
        String localizacao,

        @NotBlank(message = "O status atual é obrigatório.")
        @Size(max = 20, message = "O status atual deve ter no máximo 20 caracteres.")
        String statusAtual,

        Date ultimaAtualizacao // Pode ser opcional, dependendo do caso
) {
}
