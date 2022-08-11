package com.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	public Product findByProductId(String productId);
}
