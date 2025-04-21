package br.com.fiap.StartTrek.service;

import br.com.fiap.StartTrek.model.CondicoesClimaticas; // Importa o modelo
import br.com.fiap.StartTrek.repository.CondicoesClimaticasRepository; // Importa o repositório
import br.com.fiap.StartTrek.dto.CondicoesClimaticasDTO; // Importa o DTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CondicoesClimaticasService {

    private final CondicoesClimaticasRepository condicoesClimaticasRepository;

    @Autowired
    public CondicoesClimaticasService(CondicoesClimaticasRepository condicoesClimaticasRepository) {
        this.condicoesClimaticasRepository = condicoesClimaticasRepository;
    }

    // Criar uma nova condição climática
    public CondicoesClimaticasDTO criarCondicaoClimatica(CondicoesClimaticasDTO condicoesClimaticasDTO) {
        CondicoesClimaticas condicoesClimaticas = toEntity(condicoesClimaticasDTO);
        condicoesClimaticas.setHorarioRegistro(new Date()); // Define o horário de registro como a data atual
        CondicoesClimaticas savedCondicaoClimatica = condicoesClimaticasRepository.save(condicoesClimaticas);
        return toDTO(savedCondicaoClimatica);
    }

    // Listar todas as condições climáticas
    public List<CondicoesClimaticasDTO> listarCondicoesClimaticas() {
        return condicoesClimaticasRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar uma condição climática por ID
    public Optional<CondicoesClimaticasDTO> buscarCondicaoClimaticaPorId(Long id) {
        return condicoesClimaticasRepository.findById(id).map(this::toDTO);
    }

    // Atualizar uma condição climática existente
    public CondicoesClimaticasDTO atualizarCondicaoClimatica(Long id, CondicoesClimaticasDTO condicoesClimaticasDTO) {
        if (condicoesClimaticasRepository.existsById(id)) {
            CondicoesClimaticas condicoesClimaticas = toEntity(condicoesClimaticasDTO);
            condicoesClimaticas.setIdCondicao(id);
            condicoesClimaticas.setHorarioRegistro(new Date()); // Atualiza o horário de registro
            CondicoesClimaticas updatedCondicaoClimatica = condicoesClimaticasRepository.save(condicoesClimaticas);
            return toDTO(updatedCondicaoClimatica);
        } else {
            throw new RuntimeException("Condição climática não encontrada com ID: " + id);
        }
    }

    // Deletar uma condição climática
    public void deletarCondicaoClimatica(Long id) {
        if (condicoesClimaticasRepository.existsById(id)) {
            condicoesClimaticasRepository.deleteById(id);
        } else {
            throw new RuntimeException("Condição climática não encontrada com ID: " + id);
        }
    }

    // Método para converter CondicoesClimaticasDTO para CondicoesClimaticas
    private CondicoesClimaticas toEntity(CondicoesClimaticasDTO condicoesClimaticasDTO) {
        return new CondicoesClimaticas(
                condicoesClimaticasDTO.idCondicao(),
                condicoesClimaticasDTO.tipoCondicao(),
                condicoesClimaticasDTO.intensidade(),
                condicoesClimaticasDTO.horarioRegistro()
        );
    }

    // Método para converter CondicoesClimaticas para CondicoesClimaticasDTO
    private CondicoesClimaticasDTO toDTO(CondicoesClimaticas condicoesClimaticas) {
        return new CondicoesClimaticasDTO(
                condicoesClimaticas.getIdCondicao(),
                condicoesClimaticas.getTipoCondicao(),
                condicoesClimaticas.getIntensidade(),
                condicoesClimaticas.getHorarioRegistro()
        );
    }
}
