package com.customer.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.model.Customer;
import com.customer.model.Product;
import com.customer.repository.CustomerRepository;
import com.customer.repository.ProductRepository;
import com.customer.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		if (allProducts.size() > 0) {
			return allProducts;
		}
		return null;
	}

	@Override
	public Product getProductInfo(String productId) {
		Product product = productRepository.findByProductId(productId);
		if (Objects.nonNull(product)) {
			return product;
		}
		return null;
	}

	@Override
	public boolean addProduct(String customerId, Product product) {
		boolean check = false;
		Product dbProduct = productRepository.findByProductId(product.getProductId());
		if (Objects.isNull(dbProduct)) {
			Customer customer = customerRepository.findBycustomerId(customerId);
			if (Objects.nonNull(customer)) {
				customer.getProducts().add(product);
				Customer savedCustomer = customerRepository.save(customer);
				if (Objects.nonNull(savedCustomer)) {
					check = true;
				}

			}
		}
		return check;
	}

	@Override
	public Boolean UpdateProductInfo(String customerId, String productId, Product product) {
		boolean check = false;
		Customer customer = customerRepository.findBycustomerId(customerId);
		if (Objects.nonNull(customer)) {
			Product filtedProduct = customer.getProducts().stream()
					.filter(prod -> prod.getProductId().equalsIgnoreCase(productId)).findFirst().orElse(null);
			if (Objects.nonNull(filtedProduct)) {
				if (product.getCreatedOn() != null) {
					filtedProduct.setCreatedOn(product.getCreatedOn());
				}
				if (product.getProductInfo() != null) {
					filtedProduct.setProductInfo(product.getProductInfo());
				}
				if (product.getProductName() != null) {
					filtedProduct.setProductName(product.getProductName());
				}
				Product savedProduct = productRepository.save(filtedProduct);
				if (savedProduct != null) {
					check = true;
				}
			}
		}
		return check;
	}

	@Override
	public Boolean deleteProductInfo(String customerId, String productId) {
		boolean check = false;
		Customer customer = customerRepository.findBycustomerId(customerId);
		Product product = customer.getProducts().stream()
				.filter(prod -> prod.getProductId().equalsIgnoreCase(productId)).findFirst().orElse(null);
		if (Objects.nonNull(product)) {
			productRepository.deleteById(product.getId());
			check = true;
		}

		return check;
	}

}
