package br.com.fiap.StartTrek.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "FLUXO_TRAFEGO")
public class FluxoTrafego {

    @Id
    @Column(name = "ID_FLUXO")
    @NotNull(message = "O ID do fluxo é obrigatório.")
    private Long idFluxo;

    @ManyToOne
    @JoinColumn(name = "ID_SEMAFORO", nullable = false)
    @NotNull(message = "O semáforo é obrigatório.")
    private Semaforo semaforo;

    @NotNull(message = "A contagem de veículos é obrigatória.")
    @Min(value = 0, message = "A contagem de veículos não pode ser negativa.")
    @Column(name = "CONTAGEM_VEICULOS", nullable = false)
    private Integer contagemVeiculos;

    @NotNull(message = "O horário de registro é obrigatório.")
    @PastOrPresent(message = "O horário de registro não pode ser futuro.")
    @Column(name = "HORARIO_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioRegistro;

    // Construtor padrão
    public FluxoTrafego() {
    }

    // Construtor com parâmetros
    public FluxoTrafego(Long idFluxo, Semaforo semaforo,
                        Integer contagemVeiculos, Date horarioRegistro) {
        this.idFluxo = idFluxo;
        this.semaforo = semaforo;
        this.contagemVeiculos = contagemVeiculos;
        this.horarioRegistro = horarioRegistro;
    }

    public FluxoTrafego(Long idFluxo, Date horarioRegistro, String descricao) {
    }

    // Getters e Setters
    public Long getIdFluxo() {
        return idFluxo;
    }

    public void setIdFluxo(Long idFluxo) {
        this.idFluxo = idFluxo;
    }

    public Semaforo getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public Integer getContagemVeiculos() {
        return contagemVeiculos;
    }

    public void setContagemVeiculos(Integer contagemVeiculos) {
        this.contagemVeiculos = contagemVeiculos;
    }

    public Date getHorarioRegistro() {
        return horarioRegistro;
    }

    public void setHorarioRegistro(Date horarioRegistro) {
        this.horarioRegistro = horarioRegistro;
    }


}
