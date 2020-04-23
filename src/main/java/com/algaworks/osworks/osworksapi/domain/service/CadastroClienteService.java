package com.algaworks.osworks.osworksapi.domain.service;

import com.algaworks.osworks.osworksapi.domain.exception.DomainException;
import com.algaworks.osworks.osworksapi.domain.model.Cliente;
import com.algaworks.osworks.osworksapi.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new DomainException("Já existe um cliente cadastrado com esse e-mail");
        }
        return clienteRepository.save(cliente);
    }
}
