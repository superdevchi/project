package com.example.ecommerce.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface userRepository extends JpaRepository<userModel, Integer> {

    @Query(value = "select * from Users where username=?1 ", nativeQuery = true)
    Optional<userModel> findbyemail(String email);
}
