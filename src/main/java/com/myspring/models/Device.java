package com.myspring.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Device {

    private long id;
    @NonNull
    private String name;
    @NonNull
    private String details;
    @NonNull
    private Double price;
}
