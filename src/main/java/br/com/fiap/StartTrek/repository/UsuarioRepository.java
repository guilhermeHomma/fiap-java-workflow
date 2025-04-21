package br.com.fiap.StartTrek.repository;

import br.com.fiap.StartTrek.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email); // Retorna Optional para evitar NullPointerException
}
