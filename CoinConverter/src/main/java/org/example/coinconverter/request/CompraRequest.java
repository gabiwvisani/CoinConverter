package org.example.coinconverter.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.coinconverter.model.Cliente;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompraRequest {
    private Long idCliente;
    private TipoMoeda tipoMoeda;
    @DecimalMin(value = "0.01", message = "O valor tem que ser maior que zero")
    private BigDecimal valorEmMoedaLocal;
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
