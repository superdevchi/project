package com.example.ecommerce.Orders;

import com.example.ecommerce.Cart.cartModel;
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
@Table(name = "Orders")
public class orderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderID;

    private String orderTimeStamp;
    private String orderStatus;
    private String orderDeliveryType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cartID" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private cartModel cartModel;
}
