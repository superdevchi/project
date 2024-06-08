package com.example.ecommerce.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepository extends JpaRepository<productModel, Integer> {

    @Query(value = "select * from Product where vendorID=?1 ", nativeQuery = true)
    List<productModel> getallvendorproduct(Integer ID);
}
