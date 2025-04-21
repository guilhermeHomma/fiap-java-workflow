package br.com.fiap.StartTrek.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "CONDICOES_CLIMATICAS")
public class CondicoesClimaticas {

    @Id
    @Column(name = "ID_CONDICAO")
    @NotNull(message = "O ID da condição é obrigatório.")
    private Long idCondicao;

    @NotBlank(message = "O tipo de condição é obrigatório.")
    @Size(max = 50, message = "O tipo de condição deve ter no máximo 50 caracteres.")
    @Column(name = "TIPO_CONDICAO", nullable = false)
    private String tipoCondicao;

    @Size(max = 50, message = "A intensidade deve ter no máximo 50 caracteres.")
    @Column(name = "INTENSIDADE")
    private String intensidade;

    @NotNull(message = "O horário de registro é obrigatório.")
    @PastOrPresent(message = "O horário de registro não pode ser futuro.")
    @Column(name = "HORARIO_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioRegistro;

    // Construtor padrão
    public CondicoesClimaticas() {
    }

    // Construtor com parâmetros
    public CondicoesClimaticas(Long idCondicao, String tipoCondicao,
                               String intensidade, Date horarioRegistro) {
        this.idCondicao = idCondicao;
        this.tipoCondicao = tipoCondicao;
        this.intensidade = intensidade;
        this.horarioRegistro = horarioRegistro;
    }


    // Getters e Setters
    public Long getIdCondicao() {
        return idCondicao;
    }

    public void setIdCondicao(Long idCondicao) {
        this.idCondicao = idCondicao;
    }

    public String getTipoCondicao() {
        return tipoCondicao;
    }

    public void setTipoCondicao(String tipoCondicao) {
        this.tipoCondicao = tipoCondicao;
    }

    public String getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(String intensidade) {
        this.intensidade = intensidade;
    }

    public Date getHorarioRegistro() {
        return horarioRegistro;
    }

    public void setHorarioRegistro(Date horarioRegistro) {
        this.horarioRegistro = horarioRegistro;
    }
}
