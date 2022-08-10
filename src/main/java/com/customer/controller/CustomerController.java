package com.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.customer.Api.CustomerApi;
import com.customer.model.Customer;
import com.customer.service.CustomerService;

@RestController
public class CustomerController  implements CustomerApi{
	
	Logger logger=LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;

	@Override
	public ResponseEntity<List<Customer>> getAllCustomers() {
		logger.info("getting all customer info");
		try {
			List<Customer> allCustomer = customerService.findAllCustomer();
			return new ResponseEntity<>(allCustomer ,HttpStatus.OK);
		}
		catch(NullPointerException e)
		{
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
