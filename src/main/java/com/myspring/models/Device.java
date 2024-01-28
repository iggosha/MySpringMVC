package com.myspring.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Device {

    private long id;
    @Size(min = 1, max = 100, message = "Название товара должно иметь длину 1-100")
    private String name;
    @NonNull
    private String details;
    @DecimalMin(value = "1.0", message = "Цена товара должна быть не менее 1")
    private Double price;
}
