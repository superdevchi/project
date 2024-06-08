//package com.example.ecommerce.JWTConfiguration;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//public class vendorModelAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    vendorModelDetailServiceImplementation vendorModelDetailServiceImplementation;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    private DaoAuthenticationProvider daoAuthenticationProvider;
//
//    public vendorModelAuthenticationProvider(vendorModelDetailServiceImplementation userDetailsService, PasswordEncoder passwordEncoder) {
//        this.vendorModelDetailServiceImplementation = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }
//    @PostConstruct
//    public void init() {
//        daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(vendorModelDetailServiceImplementation);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        return daoAuthenticationProvider.authenticate(authentication);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//}
