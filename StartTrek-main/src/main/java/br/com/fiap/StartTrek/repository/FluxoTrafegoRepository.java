package br.com.fiap.StartTrek.repository;

import br.com.fiap.StartTrek.model.FluxoTrafego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FluxoTrafegoRepository extends JpaRepository <FluxoTrafego, Long> {
    // Métodos personalizados, se necessário
}
