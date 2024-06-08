package com.example.ecommerce.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Order")
public class orderController {

    @Autowired
    orderService orderService;

    @PostMapping("/create/{cartID}")
    public orderModel create(@PathVariable Integer cartID, @ModelAttribute orderModel orderModel){

        return orderService.create(orderModel, cartID);
    }

    @GetMapping("/")
    public List<orderModel> orderslist(Integer orderID){
        return orderService.list();
    }

    @PostMapping("/confirm/{orderId}")
    public String confirm(@PathVariable Integer orderId){
        orderService.confirmorder(orderId);
        return "confirmed";
    }

}
