package com.algaworks.osworks.osworksapi.api.controller;

import com.algaworks.osworks.osworksapi.api.model.OrdemServicoInput;
import com.algaworks.osworks.osworksapi.api.model.OrdemServicoModel;
import com.algaworks.osworks.osworksapi.domain.model.OrdemServico;
import com.algaworks.osworks.osworksapi.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.osworksapi.domain.service.GestaoOrdemSericoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private GestaoOrdemSericoService gestaoOrdemSericoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
        OrdemServico ordemServico = toEntity(ordemServicoInput);
        return toModel(gestaoOrdemSericoService.criar(ordemServico));
    }

    @GetMapping
    public List<OrdemServicoModel> listar() {
        return toListModel(ordemServicoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long id) {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(id);

        if (ordemServico.isPresent()) {
            return ResponseEntity.ok(toModel(ordemServico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    private OrdemServicoModel toModel(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoModel.class);
    }

    private List<OrdemServicoModel> toListModel(List<OrdemServico> ordensServico) {
        return ordensServico.stream()
                .map(ordemServico -> toModel(ordemServico))
                .collect(Collectors.toList());
    }

    private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
        return modelMapper.map(ordemServicoInput, OrdemServico.class);
    }
}
