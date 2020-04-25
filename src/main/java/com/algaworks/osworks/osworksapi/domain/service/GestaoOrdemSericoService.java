package com.algaworks.osworks.osworksapi.domain.service;

import com.algaworks.osworks.osworksapi.domain.exception.DomainException;
import com.algaworks.osworks.osworksapi.domain.model.Cliente;
import com.algaworks.osworks.osworksapi.domain.model.Comentario;
import com.algaworks.osworks.osworksapi.domain.model.OrdemServico;
import com.algaworks.osworks.osworksapi.domain.model.StatusOrdemServico;
import com.algaworks.osworks.osworksapi.domain.repository.ClienteRepository;
import com.algaworks.osworks.osworksapi.domain.repository.ComentarioRepsitory;
import com.algaworks.osworks.osworksapi.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class GestaoOrdemSericoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComentarioRepsitory comentarioRepsitory;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new DomainException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public OrdemServico finalizar(Long id) {
        OrdemServico ordemServico = buscarOrdemServico(id);
        ordemServico.finalizar();
        return ordemServicoRepository.save(ordemServico);
    }

    public OrdemServico cancelar(Long id) {
        OrdemServico ordemServico = buscarOrdemServico(id);
        ordemServico.cancelar();
        return ordemServicoRepository.save(ordemServico);
    }

    public Comentario adicionarComentario(Long ordemServicoId, String descriao) {
        OrdemServico ordemServico = buscarOrdemServico(ordemServicoId);

        Comentario comentario = new Comentario();

        comentario.setDescricao(descriao);
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setOrdemServico(ordemServico);

        return comentarioRepsitory.save(comentario);
    }

    private OrdemServico buscarOrdemServico(Long ordemServicoId) {
        return ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new DomainException("Ordem de serviço não encontrada"));
    }
}
