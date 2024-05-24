package com.sportline.service.impl;

import com.sportline.model.entity.User;
import com.sportline.repository.UserRepository;
import com.sportline.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        // Пока не требуется
        return null;
    }

    @Override
    public Page<User> findAllByPage(int pageNum, int pageSize) {
        // Пока не требуется

        return null;
    }

    @Override
    public User getById(Long id) {
        // Пока не требуется
        return null;
    }

    @Override
    public void updateById(User entity, long id) {
        // Пока не требуется
        return;
    }

    @Override
    public void deleteById(Long id) {
        // Пока не требуется
        return;
    }
}
