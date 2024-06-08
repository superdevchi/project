package com.example.ecommerce.Cart;

import com.example.ecommerce.Product.productModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface cartRepository extends JpaRepository<cartModel, Integer> {

    @Query(value = "select * from Cart where userID=?1 ", nativeQuery = true)
    List<cartModel> getallusercartitems(Integer ID);
}
