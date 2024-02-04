package com.myspring.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsCard {

    private long id;
    @Pattern(regexp = "^[A-ZА-Я][ -~а-яА-Я]{1,100}", message = "Заголовок новости должен начинаться с большой буквы и содержать 1-100 других символов")
    private String header;
    @Pattern(regexp = "^[A-ZА-Я][ -~а-яА-Я]{1,10000}", message = "Содержание новости должно начинаться с большой буквы и содержать 1-10000 других символов")
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
    @Min(value = 1, message = "ID автора должен быть не менее 1")
    private long authorId;
}
