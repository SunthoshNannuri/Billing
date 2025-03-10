package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BillingAmount;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.BillingRepository;
import com.example.demo.service.BillingService;

@RestController
@RequestMapping("/billing") 
public class Controller {

	@Autowired
    private final BillingService service;

	@Autowired
	private BillingRepository brepo;
	
    public Controller(BillingService service) {
        this.service = service;
    }



    @PostMapping("/transaction")
    public ResponseEntity<?> transaction(@RequestParam Long accountid,
                                                   @RequestParam BigDecimal amount,
                                                   @RequestParam String type) {
    	boolean isCreated=service.CreateTransaction(accountid, amount, type);
    	if(isCreated)
    	{
        return ResponseEntity.ok().body("{\"valid\":true}");
    	}
    	else
    	{
    		   return ResponseEntity.ok().body("{\"valid\":true}");
    	}
    }
    
    @PostMapping("/createcustomer")
    public ResponseEntity<Map<String, Object>> createcustomer(@RequestBody Customer customer)
    {
    	boolean isCreated=service.createcustomer(customer);
    	if(isCreated)
    	{
    		
    		BillingAmount account=brepo.findById(customer.getId()).orElseThrow(()->new RuntimeException("Account id Not Found"));
    		Long accNo = account.getCustomer_id();
    		Map<String, Object> response = new HashMap<>();
    		response.put("valid", true);
    		response.put("accountNo",accNo);
    		return ResponseEntity.ok().body(response);
    	}
    	else
    	{
    		Map<String, Object> response = new HashMap<>();
    		response.put("valid", false);
    		response.put("accountNo","Something Went Wrong");
    		return ResponseEntity.ok().body(response);
    	}
    }
    @GetMapping("/balance/{id}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long id) {
        BigDecimal balance = service.balance(id);
        return ResponseEntity.ok(balance);
    }
    
    @GetMapping("/payslips")
    public List<Transaction> payslips(@RequestParam Long account_id)
    {
    	
    	return service.payslips(account_id);
    			
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customer customer) {
        Customer foundCustomer = service.login(customer);
        
        if (foundCustomer != null) {
            return ResponseEntity.ok(foundCustomer); // Return a single customer
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
