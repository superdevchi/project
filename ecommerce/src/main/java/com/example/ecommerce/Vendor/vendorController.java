package com.example.ecommerce.Vendor;

import com.example.ecommerce.Orders.orderModel;
import com.example.ecommerce.Product.productModel;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("vendor")
public class vendorController {
    @Autowired
    vendorService vendorService;

    @PostMapping("/register")
    public vendorModel create(@ModelAttribute vendorModel vendorModel, @PathVariable(value = "file", required = false) MultipartFile thumbnail, @PathVariable(value = "file", required = false) MultipartFile vendoricon) throws StripeException {
        return vendorService.create(vendorModel,thumbnail,vendoricon);
    }

    @GetMapping("/list")
    public List<vendorModel>getallvendors(){
        return vendorService.getallvendors();
    }

    @GetMapping("/vendorproduct/{vendorID}")
    public List<productModel>getallvendors(@PathVariable Integer vendorID ){
        return vendorService.getallvendorproduct(vendorID);
    }

    @GetMapping("/Order/{vendorID}")
    public List<orderModel>getallvendororder(@PathVariable Integer vendorID){
        return vendorService.getallvendororders(vendorID);
    }

}
