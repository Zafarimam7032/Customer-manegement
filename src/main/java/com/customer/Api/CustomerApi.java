package com.customer.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.customer.model.Customer;

public interface CustomerApi {

	@GetMapping(path = "/get/all/customer")
	public ResponseEntity<List<Customer>> getAllCustomers();


}
