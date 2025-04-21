package br.com.fiap.StartTrek.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_usuarios")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "usuarioId") // Compara com base no ID
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIOS")
    @SequenceGenerator(name = "SEQ_USUARIOS", sequenceName = "SEQ_USUARIOS", allocationSize = 1)
    @Column(name = "usuario_id")
    private Long usuarioId;

    private String nome;
    private String email;
    private String senha;
    private LocalDate dtCriacao; // Alterado para LocalDate

    @Enumerated(EnumType.STRING)
    private UsuarioRole role;

    public Usuario(String nome, String email, String senha, UsuarioRole role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dtCriacao = LocalDate.now(); // Inicializa com a data atual
        this.role = role != null ? role : UsuarioRole.USER;
    }

    public Usuario(Long usuarioId, String nome, String email, String senha, LocalDate dtCriacao, UsuarioRole role) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dtCriacao = dtCriacao;
        this.role = role;
    }

    // Retorna o nome da role como String, se necessário
    public String getRoleName() {
        return this.role.name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Gera as authorities com base na role
        if (this.role == UsuarioRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.senha; // Necessário para autenticação
    }

    @Override
    public String getUsername() {
        return this.email; // Necessário para autenticação
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return this.usuarioId;
    }
}
