package br.com.fiap.StartTrek.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "SEMAFORO")
public class Semaforo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ou GenerationType.AUTO, dependendo do seu banco de dados
    @Column(name = "ID_SEMAFORO")
    @NotNull(message = "O ID do semáforo é obrigatório.")
    private Long idSemaforo;

    @NotBlank(message = "A localização é obrigatória.")
    @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres.")
    @Column(name = "LOCALIZACAO", nullable = false)
    private String localizacao;

    @NotBlank(message = "O status atual é obrigatório.")
    @Size(max = 20, message = "O status atual deve ter no máximo 20 caracteres.")
    @Column(name = "STATUS_ATUAL", nullable = false)
    private String statusAtual;

    @NotNull(message = "A última atualização é obrigatória.")
    @PastOrPresent(message = "A data da última atualização não pode ser futura.")
    @Column(name = "ULTIMA_ATUALIZACAO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaAtualizacao;

    // Construtor padrão
    public Semaforo() {
    }

    // Construtor com parâmetros
    public Semaforo(Long idSemaforo, String localizacao, String statusAtual,
                    Date ultimaAtualizacao) {
        this.idSemaforo = idSemaforo;
        this.localizacao = localizacao;
        this.statusAtual = statusAtual;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    // Getters e Setters
    public Long getIdSemaforo() {
        return idSemaforo;
    }

    public void setIdSemaforo(Long idSemaforo) {
        this.idSemaforo = idSemaforo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(String statusAtual) {
        this.statusAtual = statusAtual;
    }

    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
}
