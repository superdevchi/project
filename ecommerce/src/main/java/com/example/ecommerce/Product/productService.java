package com.example.ecommerce.Product;

import com.example.ecommerce.AWS.AWSIMAGEUPLOAD;
import com.example.ecommerce.STRIPE.stripeService;
import com.example.ecommerce.Vendor.vendorModel;
import com.example.ecommerce.Vendor.vendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class productService {

    @Autowired
    productRepository productRepository;

    @Autowired
    vendorRepository vendorRepository;

    private AWSIMAGEUPLOAD awsimageupload;

    @Autowired
    public void setAWSService(AWSIMAGEUPLOAD awsimageupload) {
        this.awsimageupload = awsimageupload;
    }

    public productModel create(Integer vendorID, productModel productModel, MultipartFile File){

//        check if vendor exist
        vendorRepository.findById(vendorID).map(
                vendorModel -> {
                    productModel.setProductVendor(vendorModel);
                    return "vendor added";
                }

        );

        MultipartFile Null = null;
        if(Null == File){
            productModel.setProductImageUrl("");

        }else {
            awsimageupload.uploadFileImage(File);
            String imageURL = awsimageupload.GETURLIMAGE();

            productModel.setProductImageUrl(imageURL);


        }

        return productRepository.save(productModel);
    }

    public List<productModel> list(){
        return productRepository.findAll();
    }

    public Optional<productModel>findproductbyID(Integer ID){
        return productRepository.findById(ID);
    }
}
