package org.example.coinconverter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente ;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private EstadoCivil estadoCivil;
    private char genero;
    public enum EstadoCivil {Casado, Solteiro, Divorciado, Viúvo};
    public Cliente(String nome, String cpf, LocalDate dataNascimento, EstadoCivil estadoCivil, char genero) {
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
