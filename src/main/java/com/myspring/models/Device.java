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
    @Size(min = 1, max = 100, message = "Имя должно быть не пустым")
    private String name;
    @NonNull
    private String details;
    @DecimalMin(value = "1.0", message = "Цена должна быть больше 0")
    private Double price;
}
