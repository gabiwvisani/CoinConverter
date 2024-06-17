package org.example.coinconverter.service;

import org.example.coinconverter.model.Cliente;
import org.example.coinconverter.model.Compra;
import org.example.coinconverter.repository.ClienteRepository;
import org.example.coinconverter.repository.CompraRepository;
import org.example.coinconverter.request.CompraRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;
    private final CotacaoMoedaService cotacaoMoedaService;

    @Autowired
    public CompraService(CompraRepository compraRepository, ClienteRepository clienteRepository, ModelMapper modelMapper, CotacaoMoedaService cotacaoMoedaService) {
        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.cotacaoMoedaService =cotacaoMoedaService;
    }

    public Long cadastrarCompra(CompraRequest compraRequest) {

        Cliente cliente = validarCliente(compraRequest.getIdCliente());
        validarValor(compraRequest.getValorEmMoedaLocal());
        Compra compra = modelMapper.map(compraRequest, Compra.class);
        compra.setCliente(cliente);
        BigDecimal valorCotacao =cotacaoMoedaService.obterAskCotacaoMoeda(compra.getTipoMoeda().toString());
        if (valorCotacao == null) {
            throw new IllegalArgumentException("Valor de cotação não pode ser nulo.");
        }
        compra.setValorCotacao(valorCotacao);
        BigDecimal valorTotalOperacao = calcularValorTotalOperacao(compra.getValorEmMoedaLocal(), valorCotacao);
        compra.setValorTotalOperacao(valorTotalOperacao);
        Compra savedCompra = compraRepository.save(compra);
        return savedCompra.getIdCompra();
    }
    public List<Compra> buscarTodasCompras(){
        List<Compra> listaComTodas = compraRepository.findAll();
        return listaComTodas;
    }

    private Cliente validarCliente(Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        return cliente.get();
    }

    private void validarValor(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor tem que ser maior que zero");
        }
    }

    private BigDecimal calcularValorTotalOperacao(BigDecimal valorEmMoedaLocal, BigDecimal valorCotacao) {
        return valorEmMoedaLocal.multiply(valorCotacao);
    }

    public Compra buscarCompra(Long id) {
        Optional<Compra> optionalCompra = compraRepository.findById(id);
        if (optionalCompra.isPresent()) {
            Compra compraPresente=optionalCompra.get();
            return compraPresente;
        } else {
            throw new NoSuchElementException("Não foi possível encontrar um registro de compra com esse id.");
        }
        }
}
