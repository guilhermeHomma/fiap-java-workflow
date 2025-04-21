package br.com.fiap.StartTrek.dto;

import java.util.Date;

public class FluxoTrafegoDTO {
        private Long idFluxo;
        private Long idSemaforo; // ID do semáforo
        private Integer contagemVeiculos;
        private Date horarioRegistro;

        // Construtor com parâmetros
        public FluxoTrafegoDTO(Long idFluxo, Long idSemaforo, Integer contagemVeiculos, Date horarioRegistro) {
                this.idFluxo = idFluxo;
                this.idSemaforo = idSemaforo; // Adicione o ID do semáforo
                this.contagemVeiculos = contagemVeiculos;
                this.horarioRegistro = horarioRegistro;
        }

        // Getters e Setters
        public Long getIdFluxo() {
                return idFluxo;
        }

        public Long getIdSemaforo() { // Getter para o ID do semáforo
                return idSemaforo;
        }

        public Integer getContagemVeiculos() {
                return contagemVeiculos;
        }

        public Date getHorarioRegistro() {
                return horarioRegistro;
        }

        // Setters
        public void setIdFluxo(Long idFluxo) {
                this.idFluxo = idFluxo;
        }

        public void setIdSemaforo(Long idSemaforo) {
                this.idSemaforo = idSemaforo;
        }

        public void setContagemVeiculos(Integer contagemVeiculos) {
                this.contagemVeiculos = contagemVeiculos;
        }

        public void setHorarioRegistro(Date horarioRegistro) {
                this.horarioRegistro = horarioRegistro;
        }
}
