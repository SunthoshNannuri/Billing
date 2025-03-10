package com.example.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BillingAmount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Long customer_id;
private BigDecimal balance=BigDecimal.ZERO;
public BillingAmount()
{
	
}
public BillingAmount(Long id, Long customer_id, BigDecimal balance) {
	super();
	this.id = id;
	this.customer_id = customer_id;
	this.balance = balance;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(Long customer_id) {
	this.customer_id = customer_id;
}
public BigDecimal getBalance() {
	return balance;
}
public void setBalance(BigDecimal balance) {
	this.balance = balance;
}
@Override
public String toString() {
	return "BillingAmount [id=" + id + ", customer_id=" + customer_id + ", balance=" + balance + "]";
}

}
