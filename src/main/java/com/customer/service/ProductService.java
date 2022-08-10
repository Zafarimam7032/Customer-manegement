package com.customer.service;

import java.util.List;

import com.customer.model.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();
	
	public Product getProductInfo(String productId);
 
	public boolean addProduct(Product product);
	
	public Boolean UpdateProductInfo(Product product);
	
	public Boolean deleteProductInfo(String productId);
	
	
	

}
