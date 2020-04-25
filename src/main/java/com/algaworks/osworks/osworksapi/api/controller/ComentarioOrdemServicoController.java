package com.algaworks.osworks.osworksapi.api.controller;

import com.algaworks.osworks.osworksapi.api.model.ComentarioInput;
import com.algaworks.osworks.osworksapi.api.model.ComentarioModel;
import com.algaworks.osworks.osworksapi.domain.exception.DomainException;
import com.algaworks.osworks.osworksapi.domain.model.Comentario;
import com.algaworks.osworks.osworksapi.domain.model.OrdemServico;
import com.algaworks.osworks.osworksapi.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.osworksapi.domain.service.GestaoOrdemSericoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioOrdemServicoController {

    @Autowired
    private GestaoOrdemSericoService gestaoOrdemSericoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ComentarioModel adicionar(@PathVariable Long ordemServicoId,
                                     @Valid @RequestBody ComentarioInput comentarioInput) {

        Comentario comentario = gestaoOrdemSericoService
                .adicionarComentario(ordemServicoId, comentarioInput.getDescricao());

        return toModel(comentario);
    }

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new DomainException("Ordem de serviço não encontrada"));

        return toListModel(ordemServico.getComentarios());
    }

    private ComentarioModel toModel(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }

    private List<ComentarioModel> toListModel(List<Comentario> comentarios) {
        return comentarios.stream()
                .map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }
}
