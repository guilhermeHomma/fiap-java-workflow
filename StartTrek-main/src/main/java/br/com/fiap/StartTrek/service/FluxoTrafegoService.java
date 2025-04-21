package br.com.fiap.StartTrek.service;

import br.com.fiap.StartTrek.model.FluxoTrafego;
import br.com.fiap.StartTrek.model.Semaforo;
import br.com.fiap.StartTrek.repository.FluxoTrafegoRepository;
import br.com.fiap.StartTrek.dto.FluxoTrafegoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FluxoTrafegoService {

    private final FluxoTrafegoRepository fluxoTrafegoRepository;

    @Autowired
    public FluxoTrafegoService(FluxoTrafegoRepository fluxoTrafegoRepository) {
        this.fluxoTrafegoRepository = fluxoTrafegoRepository;
    }

    // Criar um novo fluxo de tráfego
    public FluxoTrafegoDTO criarFluxoTrafego(FluxoTrafegoDTO fluxoTrafegoDTO) {
        FluxoTrafego fluxoTrafego = toEntity(fluxoTrafegoDTO);
        fluxoTrafego.setHorarioRegistro(new Date()); // Define o horário de registro como a data atual
        FluxoTrafego savedFluxoTrafego = fluxoTrafegoRepository.save(fluxoTrafego);
        return toDTO(savedFluxoTrafego);
    }

    // Listar todos os fluxos de tráfego
    public List<FluxoTrafegoDTO> listarFluxosTrafego() {
        return fluxoTrafegoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar um fluxo de tráfego por ID
    public Optional<FluxoTrafegoDTO> buscarFluxoTrafegoPorId(Long id) {
        return fluxoTrafegoRepository.findById(id).map(this::toDTO);
    }

    // Atualizar um fluxo de tráfego existente
    public FluxoTrafegoDTO atualizarFluxoTrafego(Long id, FluxoTrafegoDTO fluxoTrafegoDTO) {
        if (fluxoTrafegoRepository.existsById(id)) {
            FluxoTrafego fluxoTrafego = toEntity(fluxoTrafegoDTO);
            fluxoTrafego.setIdFluxo(id);
            fluxoTrafego.setHorarioRegistro(new Date()); // Atualiza o horário de registro
            FluxoTrafego updatedFluxoTrafego = fluxoTrafegoRepository.save(fluxoTrafego);
            return toDTO(updatedFluxoTrafego);
        } else {
            throw new RuntimeException("Fluxo de tráfego não encontrado com ID: " + id);
        }
    }

    // Deletar um fluxo de tráfego
    public void deletarFluxoTrafego(Long id) {
        if (fluxoTrafegoRepository.existsById(id)) {
            fluxoTrafegoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Fluxo de tráfego não encontrado com ID: " + id);
        }
    }

    // Método para converter FluxoTrafegoDTO para FluxoTrafego
    private FluxoTrafego toEntity(FluxoTrafegoDTO fluxoTrafegoDTO) {
        return new FluxoTrafego(
                fluxoTrafegoDTO.getIdFluxo(),
                new Semaforo(fluxoTrafegoDTO.getIdSemaforo(), null, null, null), // Passando o ID do semáforo
                fluxoTrafegoDTO.getContagemVeiculos(),
                fluxoTrafegoDTO.getHorarioRegistro()
        );
    }

    // Método para converter FluxoTrafego para FluxoTrafegoDTO
    private FluxoTrafegoDTO toDTO(FluxoTrafego fluxoTrafego) {
        return new FluxoTrafegoDTO(
                fluxoTrafego.getIdFluxo(),
                fluxoTrafego.getSemaforo().getIdSemaforo(), // Obtendo o ID do semáforo
                fluxoTrafego.getContagemVeiculos(),
                fluxoTrafego.getHorarioRegistro()
        );
    }
}
