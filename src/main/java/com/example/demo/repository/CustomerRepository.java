package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
	
	@Query(value = "SELECT * FROM customer WHERE name = :name AND email = :email", nativeQuery = true)
	Customer findByUser(@Param("name") String name, @Param("email") String email);


}
