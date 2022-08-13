package com.customer.service;

import java.util.List;

import com.customer.model.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();
	
	public Product getProductInfo(String productId);
 
	public boolean addProduct(Product product);
	
	public Boolean UpdateProductInfo(String productId,Product product);
	
	public Boolean deleteProductInfo(String productId);

	public Boolean assignProductToCustomer( String customerId,String productId);
	
	public Boolean updateProdcutToCustomer(String customerId,String oldproductId,String newProductId);
	
	public Boolean removeProdcutFromCustomer(String customerId,String productId);
	
	
	

}
