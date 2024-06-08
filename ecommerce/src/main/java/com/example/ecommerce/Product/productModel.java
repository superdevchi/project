package com.example.ecommerce.Product;

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
@Table(name = "Product")
public class productModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productID;

    private String productName;
    private String productDescription;
    private Integer productPrice;
    private Integer productInventoryAmount;
    private String productImageUrl;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vendorID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private vendorModel productVendor;

}
