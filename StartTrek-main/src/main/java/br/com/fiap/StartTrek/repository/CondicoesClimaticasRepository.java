package br.com.fiap.StartTrek.repository;

import br.com.fiap.StartTrek.model.CondicoesClimaticas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondicoesClimaticasRepository extends JpaRepository<
        CondicoesClimaticas, Long> {
    // Métodos personalizados, se necessário
}
