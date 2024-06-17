package org.example.coinconverter.service;

import org.example.coinconverter.controller.AwesomeApiClient;
import org.example.coinconverter.request.CotacaoMoedaRequest;
//import org.example.coinconverter.request.CotacaoMoedaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class CotacaoMoedaService {
    private final AwesomeApiClient awesomeApiClient;

    @Autowired
    public CotacaoMoedaService(AwesomeApiClient awesomeApiClient) {
        this.awesomeApiClient = awesomeApiClient;
    }
    public BigDecimal obterAskCotacaoMoeda(String moeda) {
        try {
            List<CotacaoMoedaRequest> resposta = awesomeApiClient.getCotacaoMoeda(moeda);
            if (resposta == null || resposta.isEmpty()) {
                throw new NullPointerException("A API retornou um resultado vazio para a cotação do dólar.");
            }
            CotacaoMoedaRequest cotacao = resposta.get(0);
            BigDecimal ask = cotacao.getAsk();
            if (ask == null) {
                throw new NullPointerException("O valor 'ask' retornado pela API é nulo.");
            }
            return ask;
        } catch (Exception e) {
            return null;
        }
    }
}


