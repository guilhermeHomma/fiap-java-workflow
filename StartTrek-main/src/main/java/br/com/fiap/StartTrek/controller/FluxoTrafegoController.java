package br.com.fiap.StartTrek.controller;

import br.com.fiap.StartTrek.model.FluxoTrafego;
import br.com.fiap.StartTrek.repository.FluxoTrafegoRepository;
import br.com.fiap.StartTrek.repository.SemaforoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/fluxo-trafego")
public class FluxoTrafegoController {

    @Autowired
    private FluxoTrafegoRepository fluxoTrafegoRepository;

    @Autowired
    private SemaforoRepository semaforoRepository;

    @GetMapping
    public List<FluxoTrafego> getAllFluxos() {

        return fluxoTrafegoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FluxoTrafego> getFluxoById(@PathVariable Long id) {
        return fluxoTrafegoRepository.findById(id)
                .map(fluxo -> ResponseEntity.ok().body(fluxo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FluxoTrafego> createFluxo(
            @RequestBody FluxoTrafego fluxo) {
        if (semaforoRepository.existsById(
                fluxo.getSemaforo().getIdSemaforo())) {
            FluxoTrafego savedFluxo = fluxoTrafegoRepository.save(fluxo);
            return ResponseEntity.ok().body(savedFluxo);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FluxoTrafego> updateFluxo(@PathVariable Long id,
                                                    @RequestBody FluxoTrafego fluxoDetails) {
        return fluxoTrafegoRepository.findById(id)
                .map(fluxo -> {
                    fluxo.setSemaforo(fluxoDetails.getSemaforo());
                    fluxo.setContagemVeiculos(fluxoDetails.getContagemVeiculos());
                    fluxo.setHorarioRegistro(fluxoDetails.getHorarioRegistro());
                    FluxoTrafego updated = fluxoTrafegoRepository.save(fluxo);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFluxo(@PathVariable Long id) {
        return fluxoTrafegoRepository.findById(id)
                .map(fluxo -> {
                    fluxoTrafegoRepository.delete(fluxo);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
