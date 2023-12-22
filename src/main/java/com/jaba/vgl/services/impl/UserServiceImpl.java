package com.jaba.vgl.services.impl;


import com.jaba.vgl.repositories.UserRepository;
import com.jaba.vgl.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.jaba.vgl.models.entities.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
            }
        };
    }
    public User loadUserByRefreshToken(String refreshToken) {
        Optional<User> optionalUser = userRepository.findByRefreshToken(refreshToken);
        return optionalUser.orElse(null);
    }

}
