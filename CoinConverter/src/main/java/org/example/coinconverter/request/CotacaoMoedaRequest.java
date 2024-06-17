package org.example.coinconverter.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CotacaoMoedaRequest {
    @JsonProperty("code")
    private String code;
    @JsonProperty("codein")
    private String codein;
    @JsonProperty("name")
    private String name;
    @JsonProperty("high")
    private BigDecimal high;
    @JsonProperty("low")
    private BigDecimal low;
    @JsonProperty("varBid")
    private BigDecimal varBid; // Modificado para BigDecimal
    @JsonProperty("pctChange")
    private BigDecimal pctChange; // Modificado para BigDecimal
    @JsonProperty("bid")
    private BigDecimal bid;
    @JsonProperty("ask")
    private BigDecimal ask;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("create_date")
    private String createDate;
}
