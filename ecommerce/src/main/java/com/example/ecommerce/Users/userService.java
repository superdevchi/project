package com.example.ecommerce.Users;

import com.example.ecommerce.Cart.cartModel;
import com.example.ecommerce.Cart.cartRepository;
import com.example.ecommerce.JWTConfiguration.JWTService;
import com.example.ecommerce.Orders.orderModel;
import com.example.ecommerce.Orders.orderRepository;
import com.example.ecommerce.STRIPE.stripeService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userService {

    @Autowired
    userRepository userRepository;

    @Autowired
    cartRepository cartRepository;

    @Autowired
    orderRepository orderRepository;

    @Autowired
    JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    private stripeService STRIPEService;

    @Autowired
    public void setMissionService(stripeService STRIPEService) {
        this.STRIPEService = STRIPEService;
    }

    public userModel create(userModel userModel) throws StripeException {

        String email = userModel.getUsername();
        String stripecustomerID = STRIPEService.createCustomer(email);
        userModel.setStripeID(stripecustomerID);
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        var user = userModel.builder()
                .Username(userModel.getUsername())
                .Password(userModel.getPassword())
                .StripeID(userModel.getStripeID())
                .address(userModel.getAddress())
                .build();
        userModel.setJWTToken(jwtService.generateToken(user));
        return userRepository.save(userModel);
    }

    public Optional<userModel> AuthenticateUserCredentials(userModel userModel){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userModel.getUsername(),
                        userModel.getPassword()
                )
        );

        var user = userRepository.findbyemail(userModel.getUsername()).orElseThrow();
        var jwttoken = jwtService.generateToken(user);

        userModel.setJWTToken(jwttoken);

        return userRepository.findbyemail(userModel.getUsername());
    }

    public List<userModel> list(){
        return userRepository.findAll();
    }

    public List<cartModel> getusercartlist(Integer ID){
        return cartRepository.getallusercartitems(ID);
    }

    public List<orderModel>getuserorders(Integer ID){
        return orderRepository.getuseroders(ID);
    }

}
