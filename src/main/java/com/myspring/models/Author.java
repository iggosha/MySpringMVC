package com.myspring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

// Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
// Hibernate
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @ToString.Exclude
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<NewsCard> newsCards;
}