package com.customer.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> findAllCustomer() {
		List<Customer> allCustomers = customerRepository.findAll();
		if (Objects.nonNull(allCustomers) && allCustomers.size() > 0) {
			return allCustomers;
		}
		return null;
	}

	@Override
	public Customer findCustomerByCustomerId(String customerId) {
		Customer customer = customerRepository.findBycustomerId(customerId);
		if (Objects.nonNull(customer)) {
			return customer;
		}
		return null;
	}

	@Override
	public Boolean updateCustomerDetails(String customerId, Customer customer) {
		boolean check = false;
		Customer dbCustomer = customerRepository.findBycustomerId(customerId);
		if (Objects.nonNull(dbCustomer)) {
			dbCustomer.setAddress(customer.getAddress() == null ? null : customer.getAddress());
			dbCustomer.setCustomerName(customer.getCustomerName() == null ? null : customer.getCustomerName());
			dbCustomer.setMobileNumber(customer.getMobileNumber() == null ? null : customer.getMobileNumber());
			Customer savedCustomer = customerRepository.save(dbCustomer);
			if (Objects.nonNull(savedCustomer)) {
				check = true;
			}
		}
		return check;
	}

	@Override
	public Boolean deleteCustomerDetails(String customerId) {
		boolean check = false;
		try {
			customerRepository.deleteByCustomerId(customerId);
			check = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return check;
	}

	@Override
	public Boolean AddCustomerDetails(Customer customer) {
		boolean check = false;
		Customer dbCustomer = customerRepository.findBycustomerId(customer.getCustomerId());
		if (Objects.isNull(dbCustomer)) {
			Customer saveCustomer = customerRepository.save(customer);
			if (Objects.nonNull(saveCustomer)) {
				check = true;
			}
		}
		return check;
	}

}
