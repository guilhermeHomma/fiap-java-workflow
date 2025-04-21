package br.com.fiap.StartTrek.config.security;

import br.com.fiap.StartTrek.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Lazy
    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        // Permissões para Semáforos
                        .requestMatchers(HttpMethod.POST, "/semaforos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/semaforos/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/semaforos/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/semaforos/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/semaforos").permitAll()

                        // Permissões para Fluxo de Tráfego
                        .requestMatchers(HttpMethod.POST, "/fluxo-trafego").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/fluxo-trafego/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/fluxo-trafego/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/fluxo-trafego/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/fluxo-trafego").permitAll()

                        // Permissões para Condições Climáticas
                        .requestMatchers(HttpMethod.POST, "/condicoes-climaticas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/condicoes-climaticas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/condicoes-climaticas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/condicoes-climaticas/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/condicoes-climaticas").permitAll()

                        // Qualquer outra requisição requer autenticação
                        .anyRequest().authenticated()

                )
                .addFilterBefore(verificarToken, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
