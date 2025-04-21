package br.com.fiap.StartTrek.controller;

import br.com.fiap.StartTrek.model.CondicoesClimaticas;
import br.com.fiap.StartTrek.repository.CondicoesClimaticasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/condicoes-climaticas")
@Validated // Adicionando a anotação para validação
public class CondicoesClimaticasController {

    @Autowired
    private CondicoesClimaticasRepository condicoesClimaticasRepository;

    // Método para obter todas as condições climáticas
    @GetMapping
    public List<CondicoesClimaticas> getAllCondicoes() {
        return condicoesClimaticasRepository.findAll();
    }

    // Método para obter uma condição climática específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<CondicoesClimaticas> getCondicaoById(@PathVariable Long id) {
        return condicoesClimaticasRepository.findById(id)
                .map(condicao -> ResponseEntity.ok(condicao))
                .orElse(ResponseEntity.notFound().build());
    }

    // Método para criar uma nova condição climática
    @PostMapping("/{id}")
    public ResponseEntity<CondicoesClimaticas> createCondicao(@Valid @RequestBody CondicoesClimaticas condicao) {
        CondicoesClimaticas createdCondicao = condicoesClimaticasRepository.save(condicao);
        return ResponseEntity.created(null) // Alterar para um URI se necessário
                .body(createdCondicao);
    }

    // Método para atualizar uma condição climática existente
    @PutMapping("/{id}")
    public ResponseEntity<CondicoesClimaticas> updateCondicao(
            @PathVariable Long id,
            @Valid @RequestBody CondicoesClimaticas condicaoDetails) {
        return condicoesClimaticasRepository.findById(id)
                .map(condicao -> {
                    condicao.setTipoCondicao(condicaoDetails.getTipoCondicao());
                    condicao.setIntensidade(condicaoDetails.getIntensidade());
                    condicao.setHorarioRegistro(condicaoDetails.getHorarioRegistro());
                    CondicoesClimaticas updated = condicoesClimaticasRepository.save(condicao);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Método para deletar uma condição climática
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCondicao(@PathVariable Long id) {
        return condicoesClimaticasRepository.findById(id)
                .map(condicao -> {
                    condicoesClimaticasRepository.delete(condicao);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
