package com.sportline.util;

import com.sportline.model.entity.User;
import com.sportline.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Optional<User> foundUser = userService.findByUsername(user.getUsername());
        if (foundUser.isPresent()) {
            // Имя пользователя должно быть уникальным для регистрации
            errors.rejectValue("username", "", "Пользователь с таким же именем уже существует");
        }
    }
}
