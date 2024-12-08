package com.example.user_service.service;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.common.model.DTO.UserInfoDTO;
import com.example.common.model.User;
import com.example.user_service.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            log.warn("User with username {} already exists", user.getUsername());
            return false;
        } else {
            userRepository.save(user);
            log.info("User with username {} created successfully", user.getUsername());
            return true;
        }
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Optional<User> getByUserId(Long userId) {
        return userRepository.findById(userId);
    }

    public UserInfoDTO getMyInfo() {
        User user = getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return new UserInfoDTO(1, "gosha");
    }
}