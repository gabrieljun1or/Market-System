package com.shortmarket.system.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "tb_clientes")
public class Cliente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long Id;
    @NotBlank(message = "nome is mandatory")
    private String nome;
    @NotBlank(message = "CPF,CNPJ is mandatory")
    private String CPF,CNPJ;
    @NotBlank(message = "Endereço is mandatory")
    private String Endereco;
    @NotBlank(message = "Telefone is mandatory")
    private String Telefone;
    @NotBlank(message = "Email is mandatory")
    private String Email;
    
}
