package com.example.ecommerce.JWTConfiguration;

import com.example.ecommerce.Users.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userModelDetailServiceImplementation implements UserDetailsService {

    @Autowired
    userRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findbyemail(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }
}
