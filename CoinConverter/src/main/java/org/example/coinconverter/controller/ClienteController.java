package org.example.coinconverter.controller;

import org.example.coinconverter.model.Cliente;
import org.example.coinconverter.request.ClienteRequest;
import org.example.coinconverter.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService=clienteService;
    }
    @PostMapping("/registrar/cliente")
    public ResponseEntity<?> cadastrarCliente(@RequestBody ClienteRequest clienteDto) {
        try {
            Long clienteId = clienteService.cadastrarCliente(clienteDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/buscar/clientes")
    public ResponseEntity<?> buscarTodos(){
        try {
        List<Cliente> listaComTodos = clienteService.buscarTodosClientes();
            return ResponseEntity.status(HttpStatus.CREATED).body(listaComTodos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/buscar/cliente/{id}")
    public ResponseEntity<?> buscarCompraPorID(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.buscarCliente(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
