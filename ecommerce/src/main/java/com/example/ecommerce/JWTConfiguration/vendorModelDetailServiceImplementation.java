package com.example.ecommerce.JWTConfiguration;

import com.example.ecommerce.Vendor.vendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class vendorModelDetailServiceImplementation implements UserDetailsService {

    @Autowired
    vendorRepository vendorRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return vendorRepository.findbyemail(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }
}
