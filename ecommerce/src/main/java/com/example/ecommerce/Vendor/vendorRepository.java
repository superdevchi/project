package com.example.ecommerce.Vendor;

import com.example.ecommerce.Users.userModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface vendorRepository extends JpaRepository<vendorModel, Integer> {
    @Query(value = "select * from Vendors where email=?1 ", nativeQuery = true)
    Optional<vendorModel> findbyemail(String email);
}
