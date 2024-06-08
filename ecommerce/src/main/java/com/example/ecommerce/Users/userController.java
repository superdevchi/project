package com.example.ecommerce.Users;

import com.example.ecommerce.Cart.cartModel;
import com.example.ecommerce.Orders.orderModel;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("User")
public class userController {

    @Autowired
    userService userService;

    @PostMapping("/register")
    public userModel create(@ModelAttribute userModel userModel) throws StripeException {
        return userService.create(userModel);
    }

    @PostMapping("/auth")
    public Optional<userModel>login(@RequestBody userModel userModel){
        return userService.AuthenticateUserCredentials(userModel);
    }

    @GetMapping("/list")
    public List<userModel> list(){
        return userService.list();
    }

    @GetMapping("/{USERID}")
    public List<cartModel> cartlist(@PathVariable Integer USERID ){
        return userService.getusercartlist(USERID);
    }

    @GetMapping("Order/{userID}")
    public List<orderModel>orderlist(@PathVariable Integer userID){
        return userService.getuserorders(userID);
    }
}
