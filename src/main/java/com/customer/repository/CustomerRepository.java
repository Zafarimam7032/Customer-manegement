package com.customer.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.customer.model.Customer;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findBycustomerId(String customerId);
	
	public void deleteBycustomerId(String customerId);
	
	@Modifying
	@Query(value = "update customer.product_customers set customers_id=:customerId where product_id=:productId",nativeQuery = true)
	public void assaignProductToCustomer(@Param("customerId") long customerId,@Param("productId") long productId);
	
	@Modifying
	@Query(value = "delete FROM customer.product_customers where product_id=:prodcutId and customers_id=:customerId",nativeQuery = true)
	public void removeProdcutFromCustomer(@Param("customerId") long customerId,@Param("prodcutId") long prodcutId);
}

