package com.shortmarket.system.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Entity
@Getter
@Setter
@Table(name= "tb_produtos")

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "unidade is mandatory")
    private String unidade;
    @NotBlank(message = "ncm is mandatory")
    private int ncm;
    @NotBlank(message = "valor is mandatory")
    private long valor;
}
