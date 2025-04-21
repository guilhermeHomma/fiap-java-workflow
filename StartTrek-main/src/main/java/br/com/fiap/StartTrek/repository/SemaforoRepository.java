package br.com.fiap.StartTrek.repository;

import br.com.fiap.StartTrek.model.Semaforo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemaforoRepository extends JpaRepository<Semaforo, Long> {
    // Métodos personalizados, se necessário
}
