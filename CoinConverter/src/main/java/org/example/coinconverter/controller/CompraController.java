package org.example.coinconverter.controller;

import org.example.coinconverter.model.Compra;
import org.example.coinconverter.request.CompraRequest;
import org.example.coinconverter.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompraController {
    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }
    @PostMapping("/compra")
    public ResponseEntity<?> cadastrarCompra(@RequestBody CompraRequest compraRequest) {
        try {
            Long compraId = compraService.cadastrarCompra(compraRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(compraId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/buscar/compras")
    public List<Compra> buscarTodos(){
        List<Compra> listaComTodas = compraService.buscarTodasCompras();
        return listaComTodas;
    }
    @GetMapping("/buscar/compra/{id}")
    public ResponseEntity<?> buscarCompraPorID(@PathVariable Long id) {
        try {
            Compra compra = compraService.buscarCompra(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(compra);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
