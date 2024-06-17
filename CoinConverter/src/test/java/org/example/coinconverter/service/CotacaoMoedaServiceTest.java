package org.example.coinconverter.service;

import org.example.coinconverter.controller.AwesomeApiClient;
import org.example.coinconverter.request.CotacaoMoedaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CotacaoMoedaServiceTest {
    @Mock
    private AwesomeApiClient awesomeApiClient;

    @InjectMocks
    private CotacaoMoedaService cotacaoMoedaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObterAskCotacaoMoedaUSD() {
        CotacaoMoedaRequest cotacao = new CotacaoMoedaRequest();
        cotacao.setAsk(BigDecimal.valueOf(5.0671));

        List<CotacaoMoedaRequest> resposta = Arrays.asList(cotacao);
        when(awesomeApiClient.getCotacaoMoeda("USD")).thenReturn(resposta);

        BigDecimal ask = cotacaoMoedaService.obterAskCotacaoMoeda("USD");
        assertEquals(BigDecimal.valueOf(5.0671), ask);
    }

    @Test
    public void testObterAskCotacaoMoedaEUR() {
        CotacaoMoedaRequest cotacao = new CotacaoMoedaRequest();
        cotacao.setAsk(BigDecimal.valueOf(6.1234));

        List<CotacaoMoedaRequest> resposta = Arrays.asList(cotacao);
        when(awesomeApiClient.getCotacaoMoeda("EUR")).thenReturn(resposta);

        BigDecimal ask = cotacaoMoedaService.obterAskCotacaoMoeda("EUR");
        assertEquals(BigDecimal.valueOf(6.1234), ask);
    }

    @Test
    public void testObterAskCotacaoMoedaEmpty() {
        when(awesomeApiClient.getCotacaoMoeda("")).thenReturn(null);

        BigDecimal ask = cotacaoMoedaService.obterAskCotacaoMoeda("");
        assertNull(ask);
    }

}