package br.com.fiap.StartTrek.dto;

import br.com.fiap.StartTrek.model.FluxoTrafego;

import java.util.Date;

public record FluxoTrafegoExibicaoDTO(Long idFluxo, Long idSemaforo, Integer contagemVeiculos, Date horarioRegistro) {
    public FluxoTrafegoExibicaoDTO(FluxoTrafego fluxoTrafego) {
        this(fluxoTrafego.getIdFluxo(),
                fluxoTrafego.getSemaforo().getIdSemaforo(),
                fluxoTrafego.getContagemVeiculos(),
                fluxoTrafego.getHorarioRegistro());
    }
}
