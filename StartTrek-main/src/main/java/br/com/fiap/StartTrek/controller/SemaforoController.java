package br.com.fiap.StartTrek.controller;


import br.com.fiap.StartTrek.model.Semaforo;
import br.com.fiap.StartTrek.repository.SemaforoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/semaforos")
public class SemaforoController {

    @Autowired
    private SemaforoRepository semaforoRepository;

    @GetMapping
    public List<Semaforo> getAllSemaforos() {

        return semaforoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Semaforo> getSemaforoById(@PathVariable Long id) {
        return semaforoRepository.findById(id)
                .map(semaforo -> ResponseEntity.ok().body(semaforo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Semaforo createSemaforo(@Valid @RequestBody Semaforo semaforo) {

        return semaforoRepository.save(semaforo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Semaforo> updateSemaforo(@PathVariable Long id,
                                                   @RequestBody Semaforo semaforoDetails) {
        return semaforoRepository.findById(id)
                .map(semaforo -> {
                    semaforo.setLocalizacao(semaforoDetails.getLocalizacao());
                    semaforo.setStatusAtual(semaforoDetails.getStatusAtual());
                    semaforo.setUltimaAtualizacao(
                            semaforoDetails.getUltimaAtualizacao());
                    Semaforo updated = semaforoRepository.save(semaforo);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemaforo(@PathVariable Long id) {
        return semaforoRepository.findById(id)
                .map(semaforo -> {
                    semaforoRepository.delete(semaforo);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
