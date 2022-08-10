package com.customer.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> findAllCustomer() {
		List<Customer> allCustomers = customerRepository.findAll();
		if(Objects.nonNull(allCustomers) && allCustomers.size()>0) {
			return allCustomers;
		}
		return null;
	}

	@Override
	public Customer findCustomerByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateCustomerDetails(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteCustomerDetails(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean AddCustomerDetails(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
