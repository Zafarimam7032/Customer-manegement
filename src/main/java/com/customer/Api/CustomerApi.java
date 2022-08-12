package com.customer.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.customer.model.Customer;

public interface CustomerApi {

	@GetMapping(path = "/get/all")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<List<Customer>> getAllCustomers();
	
	@GetMapping(path = "/get/{customerId}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable("customerId") String customerId);

	@PostMapping(path = "/add")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> addCustomerDetails(@RequestBody Customer customer);

	@PutMapping(path = "/update/customerId/{customerId}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> updateCustomerDetails(@PathVariable("customerId") String customerId, @RequestBody Customer customer);
	
	@DeleteMapping(path = "/delete/{customerId}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> deleteCustomerDetails(@PathVariable("customerId") String customerId);

}
