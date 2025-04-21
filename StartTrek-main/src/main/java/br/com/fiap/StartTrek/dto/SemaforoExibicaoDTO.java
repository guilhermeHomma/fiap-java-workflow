package br.com.fiap.StartTrek.dto;

import br.com.fiap.StartTrek.model.Semaforo;

import java.util.Date;

public record SemaforoExibicaoDTO(
        String localizacao,
        String statusAtual,
        Date ultimaAtualizacao
) {
    public SemaforoExibicaoDTO(Semaforo semaforo) {
        this(semaforo.getLocalizacao(), semaforo.getStatusAtual(), semaforo.getUltimaAtualizacao());
    }
}
