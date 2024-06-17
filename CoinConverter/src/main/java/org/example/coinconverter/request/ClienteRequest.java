package org.example.coinconverter.request;

import lombok.Getter;
import lombok.Setter;
import org.example.coinconverter.model.Cliente;

import java.time.LocalDate;
@Getter
@Setter
public class ClienteRequest {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Cliente.EstadoCivil estadoCivil;
    private char genero;
    public enum EstadoCivil {Casado, Solteiro, Divorciado, Viúvo};
    public ClienteRequest(String nome, String cpf, LocalDate dataNascimento, Cliente.EstadoCivil estadoCivil, char genero) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
    }

    public static String validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.chars().allMatch(Character::isDigit)) {
            return "O CPF deve conter exatamente 11 dígitos numéricos.";
        }
        return null;
    }
}
