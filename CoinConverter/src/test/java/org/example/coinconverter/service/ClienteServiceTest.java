package org.example.coinconverter.service;

import org.example.coinconverter.model.Cliente;
import org.example.coinconverter.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVerificarDuplicidadeCpfNaoDuplicado() {
        String cpf = "12345678900";
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        Cliente cliente = clienteService.verificarDuplicidadeCpf(cpf);
        assertNull(cliente);
    }

    @Test
    public void testVerificarDuplicidadeCpfDuplicado() {
        String cpf = "12345678900";
        Cliente clienteExistente = new Cliente();
        clienteExistente.setCpf(cpf);

        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteExistente));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.verificarDuplicidadeCpf(cpf);
        });

        String expectedMessage = "Já existe um cliente cadastrado com o CPF fornecido.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}