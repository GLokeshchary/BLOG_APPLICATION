package com.microservice.blogappapis.security;

import com.microservice.blogappapis.exceptions.ResourceNotFoundException;
import com.microservice.blogappapis.models.User;
import com.microservice.blogappapis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("Email with "+username+" Not found"));

        return user;
    }
}
