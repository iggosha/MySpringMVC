package com.myspring.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    private long id;
    @Pattern(regexp = "^[A-ZА-Я][ -~а-яА-Я]{1,30}", message = "Название товара должно начинаться с большой буквы и содержать 1-30 других символов")
    private String name;
    @Pattern(regexp = "^[A-ZА-Я][ -~а-яА-Я]{1,10000}", message = "Описание товара должно начинаться с большой буквы и содержать 1-10000 других символов")
    private String details;
    @DecimalMin(value = "1.0", message = "Цена товара должна быть не менее 1")
    private Double price;
}
