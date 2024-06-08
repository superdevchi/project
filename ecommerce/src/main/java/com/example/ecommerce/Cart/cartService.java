package com.example.ecommerce.Cart;

import com.example.ecommerce.Product.productRepository;
import com.example.ecommerce.Users.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class cartService {

    @Autowired
    cartRepository cartRepository;

    @Autowired
    productRepository productRepository;

    @Autowired
    userRepository userRepository;

    public cartModel create(Integer userid, Integer productid, cartModel cartModel){
        userRepository.findById(userid).map(
           userModel -> {
               cartModel.setUserModel(userModel);
               productRepository.findById(productid).map(
                     productModel -> {
                         cartModel.setProductModel(productModel);
                         return "product saved";
                     }
               );
               return "";
           }
        );
        return cartRepository.save(cartModel);
    }

    public Optional<cartModel> findbyID(Integer ID){
        return cartRepository.findById(ID);
    }

    public String delete (Integer ID){
        cartRepository.deleteById(ID);
        return "deleted";
    }

    public String updateisCartOrderSuccessful(Integer cartID){
        cartRepository.findById(cartID).map(
                cartModel -> {
                    cartModel.setIsCartOrderSuccessful("true");
                    cartRepository.save(cartModel);
                    return "Success";
                }
        );
        return "updated";
    }
}
