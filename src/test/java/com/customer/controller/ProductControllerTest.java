package com.customer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.customer.exception.BussinessException;
import com.customer.model.Customer;
import com.customer.model.Product;
import com.customer.service.ProductService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ProductControllerTest {

	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;

	Product product = null;

	@BeforeEach
	public void productInitilization() {
		product=new Product();
		product.setId(1);
		product.setProductName("test Product");
		product.setProductId("test123");
		product.setCreatedOn(LocalDate.now());
		product.setCustomers(new ArrayList<Customer>());
	}

	@Test
	void testGetAllProduct() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		List<Product> products = Arrays.asList(product);
		when(productService.getAllProducts()).thenReturn(products);
		ResponseEntity<List<Product>> allProduct = productController.getAllProduct();
		assertThat(allProduct.getStatusCode()).isEqualTo(HttpStatus.OK);
		when(productService.getAllProducts()).thenReturn(null);
		assertThrows(BussinessException.class, () -> productController.getAllProduct());
	}

	@Test
	void testGetProductDetails() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(productService.getProductInfo(Mockito.anyString())).thenReturn(product);
		ResponseEntity<Product> productDetails = productController.getProductDetails(Mockito.anyString());
		assertThat(productDetails.getStatusCode()).isEqualTo(HttpStatus.OK);
		when(productService.getProductInfo(Mockito.anyString())).thenReturn(null);
		assertThrows(BussinessException.class, () -> productController.getProductDetails(Mockito.anyString()));

	}

	@Test
	void testAddProductDetails() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(productService.addProduct(product)).thenReturn(Boolean.TRUE);
		ResponseEntity<Boolean> addProductDetails = productController.addProductDetails(product);
		assertThat(addProductDetails.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		when(productService.addProduct(product)).thenReturn(Boolean.FALSE);
		assertThrows(BussinessException.class, () -> productController.addProductDetails(product));
	}

	@Test
	void testUpdateProductDetails() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(productService.UpdateProductInfo("test123", product)).thenReturn(Boolean.TRUE);
		ResponseEntity<Boolean> updateProductDetails = productController.updateProductDetails("test123", product);
		assertThat(updateProductDetails.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		assertThrows(BussinessException.class, () ->productController.updateProductDetails("test12", product));
	}

	@Test
	void testDeleteProdcutDetails() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(productService.deleteProductInfo(Mockito.anyString())).thenReturn(Boolean.TRUE);
		ResponseEntity<Boolean> deleteProdcutDetails = productController.deleteProdcutDetails(Mockito.anyString());
		assertThat(deleteProdcutDetails.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		when(productService.deleteProductInfo(Mockito.anyString())).thenReturn(Boolean.FALSE);
		assertThrows(BussinessException.class, () -> productController.deleteProdcutDetails(Mockito.anyString()));

	}

	@Test
	void testAssignProductToCustomer() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(productService.assignProductToCustomer(Mockito.anyString(),Mockito.anyString())).thenReturn(Boolean.TRUE);
		ResponseEntity<Boolean> deleteProdcutDetails = productController.assignProductToCustomer(Mockito.anyString(),Mockito.anyString());
		assertThat(deleteProdcutDetails.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		when(productService.assignProductToCustomer(Mockito.anyString(),Mockito.anyString())).thenReturn(Boolean.FALSE);
		assertThrows(BussinessException.class, () -> productController.assignProductToCustomer(Mockito.anyString(),Mockito.anyString()));
	}

	@Test
	void testUpdateProdcutToCustomer() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(productService.updateProdcutToCustomer(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.TRUE);
		ResponseEntity<Boolean> updateProdcutToCustomer = productController.updateProdcutToCustomer(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
		assertThat(updateProdcutToCustomer.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		when(productService.updateProdcutToCustomer(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.FALSE);
		assertThrows(BussinessException.class, () -> productController.updateProdcutToCustomer(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()));

	}

	@Test
	void testRemoveProdcutFromCustomer() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(productService.removeProdcutFromCustomer(Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.TRUE);
		ResponseEntity<Boolean> removeProdcutFromCustomer = productController.removeProdcutFromCustomer(Mockito.anyString(), Mockito.anyString());
		assertThat(removeProdcutFromCustomer.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		when(productService.removeProdcutFromCustomer(Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.FALSE);
		assertThrows(BussinessException.class, () -> productController.removeProdcutFromCustomer(Mockito.anyString(), Mockito.anyString()));

	}

}
