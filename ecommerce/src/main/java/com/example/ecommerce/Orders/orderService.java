package com.example.ecommerce.Orders;

import com.example.ecommerce.Cart.cartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class orderService {

    @Autowired
    cartRepository cartRepository;

    @Autowired
    orderRepository orderRepository;

    public orderModel create( orderModel orderModel, Integer cartID){

        cartRepository.findById(cartID).map(
                cartModel -> {
                    orderModel.setCartModel(cartModel);
                    return "cart added";
                }
        );

        return orderRepository.save(orderModel);
    }

    public List<orderModel>list(){
        return orderRepository.findAll();
    }

    public String confirmorder(Integer orderID){
        orderRepository.findById(orderID).map(
                orderModel -> {
                    orderModel.setOrderStatus("confirmed");
                    orderRepository.save(orderModel);
                    return "sucess";
                }
        );
        return "order confirmed";
    }
}
