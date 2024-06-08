package com.example.ecommerce.Vendor;

import com.example.ecommerce.AWS.AWSIMAGEUPLOAD;
import com.example.ecommerce.Orders.orderModel;
import com.example.ecommerce.Orders.orderRepository;
import com.example.ecommerce.Product.productModel;
import com.example.ecommerce.Product.productRepository;
import com.example.ecommerce.STRIPE.stripeService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class vendorService {

    @Autowired
    vendorRepository vendorRepository;

    @Autowired
    orderRepository orderRepository;
    private AWSIMAGEUPLOAD awsimageupload;

    @Autowired
    public void setAWSService(AWSIMAGEUPLOAD awsimageupload) {
        this.awsimageupload = awsimageupload;
    }

    private stripeService STRIPEService;

    @Autowired
    public void setMissionService(stripeService STRIPEService) {
        this.STRIPEService = STRIPEService;
    }

    @Autowired
    productRepository productRepository;
    public vendorModel create(vendorModel vendorModel, MultipartFile thumbnail, MultipartFile vendoricon) throws StripeException {
        MultipartFile Null = null;
        if(Null == thumbnail){
            vendorModel.setThumbNailUrl("");
            if (Null == vendoricon ){
                vendorModel.setVendorIconUrl("");
            }
        }
       else {
//            awsimageupload.uploadMultipleFileImage(thumbnail,vendoricon);
            awsimageupload.uploadFileImage(thumbnail);
            String thumbnailURL = awsimageupload.GETURLIMAGE();
            String vendoriconURL = awsimageupload.GETVENDORICONIMAGE();
            vendorModel.setThumbNailUrl(thumbnailURL);
            vendorModel.setVendorIconUrl(vendoriconURL);
        }
        String email = vendorModel.getEmail();
        String stripeConnectID = STRIPEService.CreateAccountStripe(email);
        vendorModel.setStripeConnectID(stripeConnectID);

        return vendorRepository.save(vendorModel);
    }

    public List<vendorModel>getallvendors(){
        return vendorRepository.findAll();
    }

    public Optional<vendorModel>findvendorbyID(Integer ID){
        return vendorRepository.findById(ID);
    }

    public List<productModel>getallvendorproduct(Integer ID){
        return productRepository.getallvendorproduct(ID);
    }

    public List<orderModel>getallvendororders(Integer ID){
        return orderRepository.getVendorOrders(ID);
    }
}
