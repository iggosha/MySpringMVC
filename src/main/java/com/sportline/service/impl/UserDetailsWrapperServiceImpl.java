package com.sportline.service.impl;

import com.sportline.model.entity.User;
import com.sportline.repository.UserRepository;
import com.sportline.security.UserDetailsWrapper;
import com.sportline.service.UserDetailsWrapperService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsWrapperServiceImpl implements UserDetailsWrapperService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new UserDetailsWrapper(user);
    }
}
