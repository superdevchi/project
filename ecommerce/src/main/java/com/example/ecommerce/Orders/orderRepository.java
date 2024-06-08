package com.example.ecommerce.Orders;

import com.example.ecommerce.Product.productModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface orderRepository extends JpaRepository<orderModel, Integer> {

//    @Query(value = "select * from Orders where userID=?1 ", nativeQuery = true)
    @Query(value = "SELECT o FROM orderModel o " +
            "JOIN o.cartModel c " +
            "JOIN c.userModel u " +
            "WHERE u.userID = :userId")
    List<orderModel> getuseroders(Integer userId);

//    @Query(value = "select * from Orders where vendorID=?1 ", nativeQuery = true)
//    List<orderModel> getvendoroders(Integer ID);

    @Query(value = "SELECT o FROM orderModel o " +
            "JOIN o.cartModel.productModel.productVendor v " +
            "WHERE v.vendorID = :vendorId")
    List<orderModel> getVendorOrders(@Param("vendorId") Integer vendorId);
}
