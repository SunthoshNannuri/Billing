package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long account_id;
	private BigDecimal amount;
	private String type;
	@CreationTimestamp
	private LocalDateTime dateTime;
	public Transaction()
	{
		
	}
	public Transaction(Long id, Long account_id, BigDecimal amount, String type, LocalDateTime dateTime) {
		super();
		this.id = id;
		this.account_id = account_id;
		this.amount = amount;
		this.type = type;
		this.dateTime = dateTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", account_id=" + account_id + ", amount=" + amount + ", type=" + type
				+ ", dateTime=" + dateTime + "]";
	}

	
}
