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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "nome is mandatory")
    private String nome;
    @NotBlank(message = "doc is mandatory")
    private String doc;
    @NotBlank(message = "Endereço is mandatory")
    private String endereco;
    @NotBlank(message = "Telefone is mandatory")
    private String telefone;
}
