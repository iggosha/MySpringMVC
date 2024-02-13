package com.myspring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "newscards")
public class NewsCard {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "^[A-ZА-Я][ -~а-яА-Я]{1,100}", message = "Заголовок новости должен начинаться с большой буквы и содержать 1-100 других символов")
    @Column(name = "header")
    private String header;

    @Pattern(regexp = "^[A-ZА-Я][ -~а-яА-Я]{1,10000}", message = "Содержание новости должно начинаться с большой буквы и содержать 1-10000 других символов")
    @Column(name = "content")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
