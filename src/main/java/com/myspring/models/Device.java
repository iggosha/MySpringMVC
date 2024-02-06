package com.myspring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-ZА-Я][ -~а-яА-Я]{1,30}", message = "Название товара должно начинаться с большой буквы и содержать 1-30 других символов")
    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^[A-ZА-Я][ -~а-яА-Я]{1,10000}", message = "Описание товара должно начинаться с большой буквы и содержать 1-10000 других символов")
    @Column(name = "details")
    private String details;

    @DecimalMin(value = "1.0", message = "Цена товара должна быть не менее 1")
    @Column(name = "price")
    private Double price;
}
