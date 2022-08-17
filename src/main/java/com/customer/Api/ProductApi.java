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
	@GetMapping(path = "/product/get/all")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<List<Product>> getAllProduct();
	
	@GetMapping(path = "/product/get/{productId}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Product> getProductDetails(@PathVariable("productId") String productId);
	
	@PostMapping(path = "/product/add")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> addProductDetails( @RequestBody Product product);

	@PutMapping(path = "/product/update/productid/{productId}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> updateProductDetails(@PathVariable("productId") String productId,@RequestBody Product product);
	
	@DeleteMapping(path = "/product/delete/productid/{productId}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
		@ApiResponse(responseCode = "500", description = "server error"),
		@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> deleteProdcutDetails( @PathVariable("productId") String productId);

	@PutMapping(path = "/product/assign/productid/{productId}/customerid/{customerId}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
			@ApiResponse(responseCode = "500", description = "server error"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> assignProductToCustomer(@PathVariable("customerId") String customerId, @PathVariable("productId") String productId);
	
	@PutMapping(path = "update/product/customerid/{customerId}/oldproductid/{productId}/newproductid/{newproductid}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
			@ApiResponse(responseCode = "500", description = "server error"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> updateProdcutToCustomer(@PathVariable("customerId") String customerId,@PathVariable("productId") String productId,@PathVariable("newproductid") String newproductid);

	@PutMapping(path = "remove/product/customerid/{customerId}/productid/{productid}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "success"),
			@ApiResponse(responseCode = "500", description = "server error"),
			@ApiResponse(responseCode = "404", description = "service not found") })
	public ResponseEntity<Boolean> removeProdcutFromCustomer(@PathVariable("customerId") String customerid,@PathVariable("productid") String productid);
}
