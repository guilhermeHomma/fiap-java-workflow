package br.com.fiap.StartTrek.dto;

import br.com.fiap.StartTrek.model.CondicoesClimaticas;

import java.util.Date;

public record CondicoesClimaticasExibicaoDTO(Long idCondicao, String tipoCondicao, String intensidade, Date horarioRegistro) {
    public CondicoesClimaticasExibicaoDTO(CondicoesClimaticas condicoesClimaticas) {
        this(condicoesClimaticas.getIdCondicao(),
                condicoesClimaticas.getTipoCondicao(),
                condicoesClimaticas.getIntensidade(),
                condicoesClimaticas.getHorarioRegistro());
    }
}
