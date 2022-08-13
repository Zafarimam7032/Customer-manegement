package com.customer.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.model.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

	public Product findByproductId(String productId);
	
	@Modifying
	@Query(value = "update customer.customer_products set products_id=:productId where customer_id=:customerId and products_id=:oldproductId ",nativeQuery = true)
	public void updateCustomerToProduct(@Param("customerId") long customerId,@Param("oldproductId") long oldproductId,@Param("productId") long productId);
	
	@Modifying
	@Query(value = "delete FROM customer.customer_products where customer_id=:customerId and  products_id=:productId",nativeQuery = true)
	public void removeCustomerFromCustomer(@Param("customerId") long customerId,@Param("productId") long productId);
}
