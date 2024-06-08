package com.example.ecommerce.Product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class productController {
    @Autowired
    productService productService;

    @PostMapping("/create/{vendorID}")
    public productModel createproduct(@PathVariable Integer vendorID, @ModelAttribute productModel productModel, @PathVariable(value = "file", required = false) MultipartFile ImageFile){
        return productService.create(vendorID,productModel,ImageFile );
    }

    @GetMapping("/list")
    public List<productModel>list(){
        return productService.list();
    }

    @GetMapping("/search/{ID}")
    public Optional<productModel>findproductbyID(@PathVariable Integer ID){
        return productService.findproductbyID(ID);
    }
}
