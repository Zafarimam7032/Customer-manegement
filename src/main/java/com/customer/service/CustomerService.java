package com.customer.service;

import java.util.List;

import com.customer.model.Customer;

public interface CustomerService {
	
	public List<Customer> findAllCustomer();
	 
	public Customer findCustomerByCustomerId(String customerId);
	
	public Boolean updateCustomerDetails(String customerId,Customer customer);
	
	public Boolean deleteCustomerDetails(String customerId);
	
	public Boolean AddCustomerDetails(Customer customer);

}
