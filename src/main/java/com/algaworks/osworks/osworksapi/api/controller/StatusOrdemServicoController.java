package com.algaworks.osworks.osworksapi.api.controller;

import com.algaworks.osworks.osworksapi.api.model.OrdemServicoModel;
import com.algaworks.osworks.osworksapi.domain.model.OrdemServico;
import com.algaworks.osworks.osworksapi.domain.service.GestaoOrdemSericoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}")
public class StatusOrdemServicoController {

    @Autowired
    private GestaoOrdemSericoService gestaoOrdemSericoService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/finalizar")
    public OrdemServicoModel finalizaOrdemServico(@PathVariable  Long ordemServicoId) {
        return toModel(gestaoOrdemSericoService.finalizar(ordemServicoId));
    }

    @PutMapping("/cancelar")
    public OrdemServicoModel cancelarOrdemServico(@PathVariable  Long ordemServicoId) {
        return toModel(gestaoOrdemSericoService.cancelar(ordemServicoId));
    }

    private OrdemServicoModel toModel(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoModel.class);
    }
}
