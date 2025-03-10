package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.BillingAmount;

public interface BillingRepository extends JpaRepository<BillingAmount, Long>{
	 @Query(value = "SELECT * FROM billing_amount WHERE customer_id = :accountid", nativeQuery = true)
	    BillingAmount findCustomerById(@Param("accountid") Long id);

}
