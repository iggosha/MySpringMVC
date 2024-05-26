package com.sportline.service;

import com.sportline.model.entity.User;

import java.util.Optional;

public interface UserService extends CrudService<User> {

    Optional<User> findByUsername(String username);

    String getAdminPagePath();

}
