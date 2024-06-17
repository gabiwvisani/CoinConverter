package org.example.coinconverter.controller;

import org.example.coinconverter.request.CotacaoMoedaRequest;
//import org.example.coinconverter.request.CotacaoMoedaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "awesome-api", url = "https://economia.awesomeapi.com.br")
public interface AwesomeApiClient {
    @GetMapping("/{moeda}")
    List<CotacaoMoedaRequest> getCotacaoMoeda(@PathVariable("moeda") String moeda);
}
