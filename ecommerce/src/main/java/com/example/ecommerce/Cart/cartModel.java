package com.example.ecommerce.Cart;

import com.example.ecommerce.Product.productModel;
import com.example.ecommerce.Users.userModel;
import com.example.ecommerce.Vendor.vendorModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cart")
public class cartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartID;

    private Integer Amount;

    private String isCartOrderSuccessful;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userID" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private userModel userModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "productID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private productModel productModel;
}
