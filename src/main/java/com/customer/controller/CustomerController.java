package com.customer.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.customer.Api.CustomerApi;
import com.customer.model.Customer;
import com.customer.service.CustomerService;

@RestController
public class CustomerController implements CustomerApi {

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@Override
	public ResponseEntity<List<Customer>> getAllCustomers() {
		logger.info("getting all customer info");
		try {
			List<Customer> customers = customerService.findAllCustomer();
			return ResponseEntity.of(Optional.of(customers));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Customer> getCustomerDetails(String customerId) {
		logger.info("getting  customer info");
		try {
			Customer customer = customerService.findCustomerByCustomerId(customerId);
			return ResponseEntity.of(Optional.of(customer));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<Boolean> addCustomerDetails(Customer customer) {
		try {
			Boolean customerdeatislfalg = customerService.AddCustomerDetails(customer);
			if (customerdeatislfalg) {
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
	public ResponseEntity<Boolean> updateCustomerDetails(String customerId, Customer customer) {
		try {
			Boolean customerupdatefalg = customerService.updateCustomerDetails(customerId,customer);
			if (customerupdatefalg) {
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
	public ResponseEntity<Boolean> deleteCustomerDetails(String customerId) {
		try {
			Boolean customerdeletedfalg = customerService.deleteCustomerDetails(customerId);
			if (customerdeletedfalg) {
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
