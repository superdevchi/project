//package com.example.ecommerce.JWTConfiguration;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class userModelAuthenticationProvider implements AuthenticationProvider {
//    @Autowired userModelDetailServiceImplementation userModelDetailServiceImplementation;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    private DaoAuthenticationProvider daoAuthenticationProvider;
//
////    private final userModelDetailServiceImplementation userDetailsService;
////    private final PasswordEncoder passwordEncoder;
//
//    public userModelAuthenticationProvider(userModelDetailServiceImplementation userDetailsService, PasswordEncoder passwordEncoder) {
//        this.userModelDetailServiceImplementation = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }
//    @PostConstruct
//    public void init() {
//        daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userModelDetailServiceImplementation);
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
