package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BillingAmount;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.BillingRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class BillingService {
	@Autowired
	private BillingRepository brepo;

	@Autowired
	private TransactionRepository trepo;
	
	@Autowired
	private CustomerRepository crepo;



	public boolean CreateTransaction(Long accountid, BigDecimal amount, String type) {
		BillingAmount account = brepo.findCustomerById(accountid);
		
		if("charge".equalsIgnoreCase(type))
		{
			account.setBalance(account.getBalance().subtract(amount));
		}
		else if("payment".equalsIgnoreCase(type))
		{
			account.setBalance(account.getBalance().add(amount));
		}
		brepo.save(account);
		Transaction tran=new Transaction();
		tran.setAccount_id(accountid);
		tran.setAmount(amount);
		tran.setType(type);
		trepo.save(tran);
		return true;
	}



	public boolean createcustomer(Customer customer) {
		BillingAmount amount=new BillingAmount();
		Long accountid = Math.abs(UUID.randomUUID().getMostSignificantBits() % 900000000000L) + 100000000000L;
		amount.setCustomer_id(accountid);
		amount.setBalance(BigDecimal.ZERO);
		customer.setAccountNo(accountid);
		customer.setAccountNo(accountid);
		customer.setName(customer.getName());
		customer.setEmail(customer.getEmail());
		brepo.save(amount);
		crepo.save(customer);
		return true;
	}



	public BigDecimal balance(Long id) {
		

		BigDecimal bal = null;
		BillingAmount res=brepo.findCustomerById(id);
		return (res != null) ? res.getBalance() : BigDecimal.ZERO;
	}


	public List<Transaction> payslips(Long account_id) {
		
		return trepo.findByAccountNo(account_id);
		
	}



	public Customer login(Customer customer) {
	    return crepo.findByUser(customer.getName(), customer.getEmail());
	}


}
