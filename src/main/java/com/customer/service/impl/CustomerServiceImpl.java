package com.customer.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.customer.exception.BussinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.model.Customer;
import com.customer.model.Product;
import com.customer.repository.CustomerRepository;
import com.customer.repository.ProductRepository;
import com.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;

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
		String regularpatternforname="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$";
		Customer dbCustomer = customerRepository.findBycustomerId(customerId);
		if (Objects.nonNull(dbCustomer)) {
			dbCustomer.setAddress(customer.getAddress() == null ? dbCustomer.getAddress() : customer.getAddress());
			if(customer.getCustomerName() != null ){
				if(Pattern.compile(regularpatternforname).matcher(customer.getCustomerName()).matches()){
				dbCustomer.setCustomerName(customer.getCustomerName());
			}else{
					throw new BussinessException(new Date(), " Customer name not acceptable", customer.getCustomerId());
				}}
			dbCustomer.setMobileNumber(
					customer.getMobileNumber() == null ? dbCustomer.getMobileNumber() : customer.getMobileNumber());
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
			Customer customer = customerRepository.findBycustomerId(customerId);
				if (Objects.nonNull(customer)) {
					List<Product> products = customer.getProducts();
					if (products.size() > 0) {
						products.stream().forEach(prd->{
							customerRepository.removeProdcutFromCustomer(customer.getId(), prd.getId());
							productRepository.removeCustomerFromCustomer(customer.getId(), prd.getId());
						});
					}
					customerRepository.deleteById(customer.getId());
					check = true;
				}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return check;
	}

	@Override
	public Boolean AddCustomerDetails(Customer customer) {
		boolean check = false;
		String regularpatternforname="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$";
		String regularpattern="^((?=[A-Za-z0-9])(?![_\\\\-]).)*$";
		if ( Pattern.compile(regularpatternforname).matcher(customer.getCustomerName()).matches() && Pattern.compile(regularpattern).matcher(customer.getCustomerId()).matches()){
			Customer dbCustomer = customerRepository.findBycustomerId(customer.getCustomerId());
		if (Objects.isNull(dbCustomer)) {
			Customer saveCustomer = customerRepository.save(customer);
			if (Objects.nonNull(saveCustomer)) {
				check = true;
			}
		}}
		return check;
	}

}
