package com.sportline.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 100, message = "Имя пользователя должно содержать 1-100 символов")
    private String username;

    @Past(message = "Дата рождения должна быть в прошлом")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Дата не должна быть пустой")
    private LocalDate dateOfBirth;

    @Size(min = 6, message = "Пароль должен содержать >= 6 символов")
    private String password;
}
