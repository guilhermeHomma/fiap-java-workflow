package br.com.fiap.StartTrek.controller;

import br.com.fiap.StartTrek.config.security.TokenService;
import br.com.fiap.StartTrek.config.security.VerificarToken;
import br.com.fiap.StartTrek.dto.LoginDTO;
import br.com.fiap.StartTrek.dto.UsuarioCadastroDTO;
import br.com.fiap.StartTrek.dto.UsuarioExibicaoDTO;
import br.com.fiap.StartTrek.model.Usuario;
import br.com.fiap.StartTrek.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private VerificarToken verificarToken;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginDTO usuarioDto) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(usuarioDto.email(), usuarioDto.senha());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok().build();
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UsuarioExibicaoDTO> registrar(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO) {
        UsuarioExibicaoDTO usuarioSalvo = usuarioService.salvarUsuario(usuarioCadastroDTO);
        return ResponseEntity.ok(usuarioSalvo);
    }
}
