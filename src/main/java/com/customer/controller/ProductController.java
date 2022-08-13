package com.customer.controller;

import java.util.List;
import java.util.Optional;

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
			return ResponseEntity.of(Optional.of(products));
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
			return ResponseEntity.of(Optional.of(product));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Boolean> addProductDetails( Product product) {
		try {
			boolean addProductflag = productService.addProduct( product);
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
	public ResponseEntity<Boolean> updateProductDetails(String productId, Product product) {
		try {
			Boolean updateProductInfoFlag = productService.UpdateProductInfo(productId, product);
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
	public ResponseEntity<Boolean> deleteProdcutDetails(String productId) {
		try {
			Boolean deleteProductInfoFlag = productService.deleteProductInfo(productId);
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

	@Override
	public ResponseEntity<Boolean> assignProductToCustomer(String customerId, String productId) {
		try {
			Boolean assignProductFlag =productService.assignProductToCustomer(customerId,productId);
			if (assignProductFlag) {
				return new ResponseEntity<>(HttpStatus.ACCEPTED);

			} else {
				logger.error("Can not assign the product to the customer");
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Boolean> updateProdcutToCustomer(String customerId, String oldProductId,String newProductId) {
		try {
			Boolean updateProductFlag =productService.updateProdcutToCustomer(customerId, oldProductId, newProductId);
			if (updateProductFlag) {
				return new ResponseEntity<>(HttpStatus.ACCEPTED);

			} else {
				logger.error("Can not  the product to the customer");
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<Boolean> removeProdcutFromCustomer(String customerid, String productid) {

		try {
			Boolean removeProductFlag =productService.removeProdcutFromCustomer(customerid, productid);
			if (removeProductFlag) {
				return new ResponseEntity<>(HttpStatus.ACCEPTED);

			} else {
				logger.error("Can not remove product to the customer");
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
