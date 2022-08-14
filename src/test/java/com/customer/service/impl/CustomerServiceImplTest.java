package com.customer.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.customer.model.Customer;
import com.customer.model.Product;
import com.customer.repository.CustomerRepository;
import com.customer.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	ProductRepository productRepository;

	Product product = null;

	@BeforeEach
	public void setUp() {
		product = new Product(1, "test", "test123", LocalDate.now(), "test info", new ArrayList<Customer>());
	}

	@Test
	void findAllCustomerTest() {
		List<Product> products = Arrays.asList(product);
		when(productRepository.findAll()).thenReturn(products);
		lenient().when(productServiceImpl.getAllProducts()).thenReturn(products);
		when(productRepository.findAll()).thenReturn(new ArrayList<Product>());
		lenient().when(productServiceImpl.getAllProducts()).thenReturn(new ArrayList<Product>());
	}

	@Test
	void findCustomerByCustomerIdTest() {
		when(productRepository.findByproductId(Mockito.anyString())).thenReturn(product);
		lenient().when(productServiceImpl.getProductInfo(Mockito.anyString())).thenReturn(product);
		when(productRepository.findByproductId(Mockito.anyString())).thenReturn(null);
		lenient().when(productServiceImpl.getProductInfo(Mockito.anyString())).thenReturn(null);
	}

	@Test
	void addProductTest() {
		when(productRepository.findByproductId(Mockito.anyString())).thenReturn(null);
		when(productRepository.save(Mockito.any())).thenReturn(product);
		lenient().when(productServiceImpl.addProduct(product)).thenReturn(Boolean.TRUE);
		when(productRepository.findByproductId(Mockito.anyString())).thenReturn(product);
		assertFalse(productServiceImpl.addProduct(product));
	}

	@Test
	void updateProductInfoTest() {
		when(productRepository.findByproductId(Mockito.anyString())).thenReturn(product);
		Product updateProduct = new Product(3, "test update", "test id", null, "update test info", null);
		when(productRepository.save(Mockito.any())).thenReturn(product);
		lenient().when(productServiceImpl.UpdateProductInfo(Mockito.anyString(), updateProduct))
				.thenReturn(Boolean.TRUE);
		when(productRepository.findByproductId(Mockito.anyString())).thenReturn(null);
		assertFalse(productServiceImpl.UpdateProductInfo(Mockito.anyString(), updateProduct));

	}

	@Test
	void deleteCustomerDetailsTest() {
		when(productRepository.findByproductId(Mockito.anyString())).thenReturn(product);
		assertTrue(productServiceImpl.deleteProductInfo(Mockito.anyString()));
		verify(productRepository, times(1)).deleteById(Mockito.anyLong());
		List<Customer> customers = Arrays
				.asList(new Customer(1, "test name", "1s23456789", "test address", "1234", null));
		product.setCustomers(customers);
		when(productRepository.findByproductId(Mockito.anyString())).thenReturn(product);
		assertTrue(productServiceImpl.deleteProductInfo(Mockito.anyString()));
		verify(customerRepository, times(1)).removeProdcutFromCustomer(Mockito.anyLong(), Mockito.anyLong());
		verify(productRepository, times(1)).removeCustomerFromCustomer(Mockito.anyLong(), Mockito.anyLong());
		verify(productRepository, times(2)).deleteById(Mockito.anyLong());
	}

	@Test
	void assignProductToCustomerTest() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setCustomerName("test Name");
		customer.setMobileNumber("123456789");
		customer.setCustomerId("test123");
		customer.setAddress("test address");
		customer.setProducts(new ArrayList<Product>());
		List<Product> products = Arrays.asList(product);
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(customer);
		when(productRepository.findByproductId(Mockito.anyString())).thenReturn(product);
		when(productRepository.save(Mockito.any())).thenReturn(product);
		when(customerRepository.save(Mockito.any())).thenReturn(customer);
		lenient().when(productServiceImpl.assignProductToCustomer(Mockito.anyString(), "123")).thenReturn(Boolean.TRUE);
		customer.setProducts(products);
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(customer);
		assertFalse(productServiceImpl.assignProductToCustomer(Mockito.anyString(), "test123"));

	}

	@Test
	void updateProdcutToCustomerTest() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setCustomerName("test Name");
		customer.setMobileNumber("123456789");
		customer.setCustomerId("test123");
		customer.setAddress("test address");
		customer.setProducts(new ArrayList<Product>());
		List<Product> products = Arrays.asList(product);
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(customer);
		assertFalse(productServiceImpl.updateProdcutToCustomer(Mockito.anyString(), "test123", "test1"));
		customer.setProducts(products);
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(customer);
		when(productRepository.findByproductId(Mockito.anyString())).thenReturn(product);
		when(productRepository.save(Mockito.any())).thenReturn(product);
		lenient().when(productServiceImpl.updateProdcutToCustomer(Mockito.anyString(), "test123", "test1"))
				.thenReturn(Boolean.TRUE);
		verify(productRepository, times(1)).updateCustomerToProduct(Mockito.anyLong(), Mockito.anyLong(),
				Mockito.anyLong());
		verify(customerRepository, times(1)).removeProdcutFromCustomer(Mockito.anyLong(), Mockito.anyLong());
	}

	@Test
	void removeProdcutFromCustomerTest() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setCustomerName("test Name");
		customer.setMobileNumber("123456789");
		customer.setCustomerId("test123");
		customer.setAddress("test address");
		customer.setProducts(new ArrayList<Product>());
		List<Product> products = Arrays.asList(product);
		List<Customer> customers = Arrays.asList(customer);
		customer.setProducts(products);
		product.setCustomers(customers);
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(customer);
		when(productRepository.findByproductId(Mockito.anyString())).thenReturn(product);
		assertTrue(productServiceImpl.removeProdcutFromCustomer("test123", "test123"));
		verify(productRepository, times(1)).removeCustomerFromCustomer(Mockito.anyLong(), Mockito.anyLong());
		verify(customerRepository, times(1)).removeProdcutFromCustomer(Mockito.anyLong(), Mockito.anyLong());
	}
}