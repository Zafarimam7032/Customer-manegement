package com.customer.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.customer.model.Product;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ProductApi {
	@GetMapping(path = "/get/all/product")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<List<Product>> getAllProduct();
	
	@GetMapping(path = "/get/{productId}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Product> getProductDetails(@PathVariable("productId") String productId);
	
	@PostMapping(path = "/add/{customerId}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> addProductDetails(@PathVariable("customerId") String customerId, @RequestBody Product product);

	@PutMapping(path = "/update/customerid/{customerId}/productid/{productId}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> updateProductDetails(@PathVariable("customerId") String customerId, @PathVariable("productId") String productId,@RequestBody Product product);
	
	@DeleteMapping(path = "/delete/customerId/{customerId}/productid/{productId}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> deleteProdcutDetails(@PathVariable("customerId") String customerId, @PathVariable("productId") String productId);

}
