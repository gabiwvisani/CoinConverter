package org.example.coinconverter.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Validated
@EqualsAndHashCode
@NoArgsConstructor
public class Compra {
    public Compra(Cliente cliente, TipoMoeda tipoMoeda, BigDecimal valorEmMoedaLocal, BigDecimal valorCotacao, BigDecimal valorTotalOperacao, String numeroAgencia) throws Exception {
        this.cliente = cliente;
        this.tipoMoeda = tipoMoeda;
        setValorEmMoedaLocal(valorEmMoedaLocal);
        this.valorCotacao = valorCotacao;
        this.valorTotalOperacao = valorTotalOperacao;
        this.numeroAgencia = numeroAgencia;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;
    @ManyToOne
    private Cliente cliente;
    private TipoMoeda tipoMoeda;
    @DecimalMin(value = "0.01", message = "O valor tem que ser maior que zero")
    private BigDecimal valorEmMoedaLocal;
  //  private BigDecimal valorEmMoedaEstrangeira;
    private BigDecimal valorCotacao;
    private BigDecimal valorTotalOperacao;
    @Pattern(regexp = "^(?!0000)\\d{4}$", message = "Número da agência inválido")
    private String numeroAgencia;
    public enum TipoMoeda {USD, EUR};

    public void setValorEmMoedaLocal(BigDecimal valorEmMoedaLocal) throws Exception {
        if(valorEmMoedaLocal.compareTo(BigDecimal.ZERO) > 0){
            this.valorEmMoedaLocal=valorEmMoedaLocal;
        }else {
            throw new Exception("O valor tem que ser maior que zero");
        }
    }
}
