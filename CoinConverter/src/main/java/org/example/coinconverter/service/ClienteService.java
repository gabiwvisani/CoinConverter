package org.example.coinconverter.service;

import org.example.coinconverter.model.Cliente;
import org.example.coinconverter.repository.ClienteRepository;
import org.example.coinconverter.request.ClienteRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository,ModelMapper modelMapper) {
        this.modelMapper=modelMapper;
        this.clienteRepository = clienteRepository;
    }
    public Long cadastrarCliente(ClienteRequest clienteDto) {
        String cpf = clienteDto.getCpf();
        String cpfValidationMessage = Cliente.validarCPF(cpf);
        if (cpfValidationMessage != null) {
            throw new IllegalArgumentException(cpfValidationMessage);
        }
        verificarDuplicidadeCpf(cpf);
        Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
        Cliente savedCliente = clienteRepository.save(cliente);
        return savedCliente.getIdCliente();
    }
    public List<Cliente> buscarTodosClientes(){
        List<Cliente> listaComTodos = clienteRepository.findAll();
        return listaComTodos;
    }
    public Cliente buscarCliente(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente clientePresente=optionalCliente.get();
            return clientePresente;
        } else {
            throw new NoSuchElementException("Não foi possível encontrar um registro de cliente com esse id.");
        }
    }
    public Cliente verificarDuplicidadeCpf(String cpf) {
        Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cpf);
        if (clienteExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com o CPF fornecido.");
        }
        return null;
    }
}
