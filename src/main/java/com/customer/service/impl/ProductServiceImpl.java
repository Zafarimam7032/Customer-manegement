package com.customer.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
		Product product = productRepository.findByproductId(productId);
		if (Objects.nonNull(product)) {
			return product;
		}
		return null;
	}

	@Override
	public boolean addProduct(Product product) {
		boolean check = false;
		String regularpatternforname="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$";
		String regularpattern="^((?=[A-Za-z0-9])(?![_\\\\-]).)*$";
		if ( Pattern.compile(regularpatternforname).matcher(product.getProductName()).matches() && Pattern.compile(regularpattern).matcher(product.getProductId()).matches()){
			Product dbProduct = productRepository.findByproductId(product.getProductId());
		if (Objects.isNull(dbProduct)) {
			Product saveproduct = productRepository.save(product);
			if (Objects.nonNull(saveproduct)) {
				check = true;
			}
		}}
		return check;
	}

	@Override
	public Boolean UpdateProductInfo(String productId, Product product) {
		boolean check = false;
		String regularpatternforname="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$";
		String regularpattern="^((?=[A-Za-z0-9])(?![_\\\\-]).)*$";
		Product dbProduct = productRepository.findByproductId(productId);
		if (Objects.nonNull(dbProduct)) {
			if(product.getProductId() != null ){
				if(Pattern.compile(regularpattern).matcher(product.getProductId()).matches()){
					dbProduct.setProductId(product.getProductId());
				}else{
					throw new BussinessException(new Date(), "  product id not acceptable", product.getProductId());
				}}
			dbProduct.setProductInfo(
					product.getProductInfo() == null ? dbProduct.getProductInfo() : product.getProductInfo());
				if(product.getProductName() != null ){
					if(Pattern.compile(regularpatternforname).matcher(product.getProductName()).matches()){
						dbProduct.setProductName(product.getProductName());
					}else{
						throw new BussinessException(new Date(), "  product name not acceptable", product.getProductId());
					}}
			Product saveProduct = productRepository.save(dbProduct);
			if (Objects.nonNull(saveProduct)) {
				check = true;
			}
		}
		return check;
	}

	@Override
	public Boolean deleteProductInfo(String productId) {
		boolean check = false;
		Product product = productRepository.findByproductId(productId);
		List<Customer> customers = product.getCustomers();
		if (Objects.nonNull(product)) {
			if (customers.size() > 0) {
				customers.stream().forEach(cust -> {
					customerRepository.removeProdcutFromCustomer(cust.getId(), product.getId());
					productRepository.removeCustomerFromCustomer(cust.getId(), product.getId());
				});
			}
			productRepository.deleteById(product.getId());
			check = true;
		}
		return check;
	}

	@Override
	public Boolean assignProductToCustomer(String customerId, String productId) {
		Boolean check = false;
		Customer customer = customerRepository.findBycustomerId(customerId);
		Product filterProduct = customer.getProducts().stream()
				.filter(prd -> prd.getProductId().equalsIgnoreCase(productId)).findFirst().orElse(null);
		if (Objects.nonNull(customer) && Objects.isNull(filterProduct)) {
			Product product = productRepository.findByproductId(productId);
			Customer filterCustomer = product.getCustomers().stream()
					.filter(cstmr -> cstmr.getCustomerId().equalsIgnoreCase(customerId)).findFirst().orElse(null);
			if (Objects.nonNull(product) && Objects.isNull(filterCustomer)) {
				try {
					customer.getProducts().add(product);
					product.getCustomers().add(customer);
					Product savedProduct = productRepository.save(product);
					Customer savedCustomer = customerRepository.save(customer);
					if (Objects.nonNull(savedProduct) && Objects.nonNull(savedCustomer)) {
						check = true;
					}
				} catch (Exception e) {
					logger.error("error in assaingin product");
					logger.error(e.getMessage());
				}
			}
		}
		return check;
	}

	@Override
	public Boolean updateProdcutToCustomer(String customerId, String oldProductId, String newProductid) {
		boolean check = false;
		Customer customer = customerRepository.findBycustomerId(customerId);
		Product filterProduct = customer.getProducts().stream()
				.filter(prd -> prd.getProductId().equalsIgnoreCase(oldProductId)).findFirst().orElse(null);
		Product productCheck = customer.getProducts().stream()
				.filter(prduct -> prduct.getProductId().equalsIgnoreCase(newProductid)).findFirst().orElse(null);
		Product produt = productRepository.findByproductId(newProductid);
		if (Objects.nonNull(customer) && Objects.nonNull(filterProduct) && Objects.isNull(productCheck)
				&& Objects.nonNull(produt)) {

			productRepository.updateCustomerToProduct(customer.getId(), filterProduct.getId(),produt.getId());


			Customer filterCustomer = produt.getCustomers().stream()
					.filter(cust -> cust.getCustomerId().equalsIgnoreCase(customerId)).findFirst().orElse(null);
			customerRepository.removeProdcutFromCustomer(customer.getId(), filterProduct.getId());
			if (Objects.isNull(filterCustomer)) {
				produt.getCustomers().add(customer);
				Product savedProduct = productRepository.save(produt);
				if (Objects.nonNull(savedProduct)) {
					check = true;
				}
			}
		}

		return check;
	}

	@Override
	public Boolean removeProdcutFromCustomer(String customerId, String productId) {
		boolean check = false;
		Customer customer = customerRepository.findBycustomerId(customerId);
		Product filterProduct = customer.getProducts().stream()
				.filter(prd -> prd.getProductId().equalsIgnoreCase(productId)).findFirst().orElse(null);
		try {
			if (Objects.nonNull(filterProduct)) {
				productRepository.removeCustomerFromCustomer(customer.getId(), filterProduct.getId());
			}
			Product product = productRepository.findByproductId(productId);
			Customer filterCustomer = product.getCustomers().stream()
					.filter(cust -> cust.getCustomerId().equalsIgnoreCase(customerId)).findFirst().orElse(null);
			if (Objects.nonNull(filterCustomer)) {
				customerRepository.removeProdcutFromCustomer(filterCustomer.getId(), product.getId());
			}
			check = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return check;
	}
}
