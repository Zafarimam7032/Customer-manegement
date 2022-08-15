package com.customer.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	CustomerServiceImpl customerService;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	ProductRepository productRepository;
	
	Customer customer=null;
	@BeforeEach
	public void customerInit()
	{
		customer=new Customer(1, "test customer", "23456789", "test addresss", "test123", new ArrayList<Product>());
	}

	@Test
	void findAllCustomerTest() {
		List<Customer> customers=Arrays.asList(customer);
		when(customerRepository.findAll()).thenReturn(customers);
		lenient().when(customerService.findAllCustomer()).thenReturn(customers);
		when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>());
		lenient().when(customerService.findAllCustomer()).thenReturn(null);
	}

	@Test
	void findCustomerByCustomerIdTest() {
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(customer);
		lenient().when(customerService.findCustomerByCustomerId(Mockito.anyString())).thenReturn(customer);
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(null);
		lenient().when(customerService.findCustomerByCustomerId(Mockito.anyString())).thenReturn(null);
		
	}

	@Test
	void updateCustomerDetailsTest() {
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(customer);
        Customer updateCustomer=new Customer(0, "test update", "987654321", "updated test address", null, null);
		when(customerRepository.save(Mockito.any())).thenReturn(customer);
        lenient().when(customerService.updateCustomerDetails(Mockito.anyString(), updateCustomer)).thenReturn(Boolean.TRUE);
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(new Customer());
        lenient().when(customerService.updateCustomerDetails(Mockito.anyString(), updateCustomer)).thenReturn(Boolean.TRUE);

	}

	@Test
	void deleteCustomerDetailsTest() {
		Product product=new Product();
		product.setId(1);
		product.setProductName("test product");
		product.setProductInfo("test prodcut info");
		product.setCreatedOn(LocalDate.now());
		product.setProductId("test123");
		List<Product> products=Arrays.asList(product);
		customer.setProducts(products);
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(customer);
		assertTrue(customerService.deleteCustomerDetails(Mockito.anyString()));
		verify(customerRepository,times(1)).removeProdcutFromCustomer(Mockito.anyLong(), Mockito.anyLong());
		verify(productRepository , times(1)).removeCustomerFromCustomer(Mockito.anyLong(), Mockito.anyLong());
		verify(customerRepository,times(1)).deleteById(Mockito.anyLong());
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(null);
		assertFalse(customerService.deleteCustomerDetails(Mockito.anyString()));

	}

	@Test
	void testAddCustomerDetails() {
		when(customerRepository.findBycustomerId("test123")).thenReturn(null);
		when(customerRepository.save(Mockito.any())).thenReturn(customer);
		lenient().when(customerService.AddCustomerDetails(customer)).thenReturn(Boolean.TRUE);
		when(customerRepository.findBycustomerId(Mockito.anyString())).thenReturn(customer);
		assertFalse(customerService.AddCustomerDetails(customer));
	}
}
