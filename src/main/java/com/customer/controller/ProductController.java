package com.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.customer.Api.ProductApi;
import com.customer.model.Product;
import com.customer.service.ProductService;

@RestController
public class ProductController implements ProductApi {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductService productService;

	@Override
	public ResponseEntity<List<Product>> getAllProduct() {
		logger.info("getting all product info");
		try {
			List<Product> products = productService.getAllProducts();
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Product> getProductDetails(String productId) {
		try {
			Product product = productService.getProductInfo(productId);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Boolean> addProductDetails(String customerId, Product product) {
		try {
			boolean addProductflag = productService.addProduct(customerId, product);
			if (addProductflag) {
				return new ResponseEntity<>(HttpStatus.ACCEPTED);

			} else {
				logger.error("customer deatials not acceptable");
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<Boolean> updateProductDetails(String customerId, String productId, Product product) {
		try {
			Boolean updateProductInfoFlag = productService.UpdateProductInfo(customerId, productId, product);
			if (updateProductInfoFlag) {
				return new ResponseEntity<>(HttpStatus.ACCEPTED);

			} else {
				logger.error(" can not update customer deatials not acceptable");
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Boolean> deleteProdcutDetails(String customerId, String productId) {
		try {
			Boolean deleteProductInfoFlag = productService.deleteProductInfo(customerId,productId);
			if (deleteProductInfoFlag) {
				return new ResponseEntity<>(HttpStatus.ACCEPTED);

			} else {
				logger.error(" can not delete customer deatials not acceptable");
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
