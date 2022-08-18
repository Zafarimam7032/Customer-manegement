package com.customer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.customer.exception.BussinessException;
import com.customer.model.Customer;
import com.customer.model.Product;
import com.customer.service.CustomerService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class CustomerControllerTest {

	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerService customerService;

	Customer customer = null;

	@BeforeEach
	public void customerInitiate() {
		customer = new Customer(1, "test customer", "123456789", "test address", "test123", null);
		List<Product> products = new ArrayList<Product>();
		customer.setProducts(products);
	}

	@Test
	void getAllCustomersTest() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		List<Customer> customers = Arrays.asList(customer);
		when(customerService.findAllCustomer()).thenReturn(customers);
		ResponseEntity<List<Customer>> allCustomers = customerController.getAllCustomers();
		assertThat(allCustomers.getStatusCodeValue()).isEqualTo(200);
		when(customerService.findAllCustomer()).thenReturn(null);
		ResponseEntity<List<Customer>> allCustomerswithNull = customerController.getAllCustomers();
		assertThat(allCustomerswithNull.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	void testGetCustomerDetails() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(customerService.findCustomerByCustomerId(Mockito.anyString())).thenReturn(customer);
		ResponseEntity<Customer> customerDetails = customerController.getCustomerDetails(Mockito.anyString());
		assertThat(customerDetails.getStatusCodeValue()).isEqualTo(200);
		when(customerService.findCustomerByCustomerId(Mockito.anyString())).thenReturn(null);
		assertThrows(BussinessException.class,()-> customerController.getCustomerDetails(Mockito.anyString()));
	}

	@Test
	void testAddCustomerDetails() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(customerService.AddCustomerDetails(customer)).thenReturn(Boolean.TRUE);
		assertThrows(NullPointerException.class,()-> customerController.addCustomerDetails(Mockito.any()));
		ResponseEntity<Boolean> addCustomerDetails = customerController.addCustomerDetails(customer);
		assertThat(addCustomerDetails.getStatusCodeValue()).isEqualTo(202);
		assertThrows(NullPointerException.class,()-> customerController.addCustomerDetails(Mockito.any()));
	}

	@Test
	void testUpdateCustomerDetails() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		lenient().when(customerService.updateCustomerDetails("test12", customer)).thenReturn(Boolean.TRUE);
		assertThrows(BussinessException.class,()-> customerController.updateCustomerDetails(Mockito.any(),customer));
		lenient().when(customerService.updateCustomerDetails("test1", customer)).thenReturn(Boolean.TRUE);
		assertThrows(BussinessException.class, ()->customerController.updateCustomerDetails(Mockito.anyString(), customer));
		lenient().when(customerService.updateCustomerDetails("test123", customer)).thenReturn(Boolean.TRUE);
		ResponseEntity<Boolean> updateCustomerDetails = customerController.updateCustomerDetails("test123", customer);
		assertThat(updateCustomerDetails.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

	}

	@Test
	void testDeleteCustomerDetails() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	lenient().when(customerService.deleteCustomerDetails(Mockito.anyString())).thenReturn(Boolean.TRUE);
		ResponseEntity<Boolean> deleteCustomerDetails = customerController.deleteCustomerDetails(Mockito.anyString());
		assertThat(deleteCustomerDetails.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		
		lenient().when(customerService.deleteCustomerDetails("test12")).thenReturn(Boolean.FALSE);
		assertThrows(BussinessException.class, ()-> customerController.deleteCustomerDetails("test12"));

	}

}
