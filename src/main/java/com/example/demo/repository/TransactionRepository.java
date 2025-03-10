package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	
	@Query(value="select * from transaction where account_id=:account_id",nativeQuery = true)
	List<Transaction> findByAccountNo(@Param("account_id") Long account_id);
	
}
