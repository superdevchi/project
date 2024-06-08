package com.example.ecommerce.Cart;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("Cart")
public class cartController {

    @Autowired
    cartService cartService;

    @PostMapping("/create/{userid}/{productid}")
    public cartModel create(@PathVariable Integer userid, @PathVariable Integer productid, cartModel cartModel){
        return cartService.create(userid, productid, cartModel);
    }

    @GetMapping ("/search/{cartID}")
    public Optional<cartModel> find(@PathVariable Integer cartID){
        return cartService.findbyID(cartID);
    }

    @DeleteMapping("/delete/{cartID}")
    public String delete(@PathVariable Integer cartID){
        cartService.delete(cartID);
        return "deleted";
    }

    @PostMapping("/updateorder/{cartID}")
    public String update(@PathVariable Integer cartID){
        cartService.updateisCartOrderSuccessful(cartID);
        return "updated";
    }
}
