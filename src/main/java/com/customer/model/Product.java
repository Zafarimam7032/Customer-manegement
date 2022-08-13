package com.customer.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "product_Name")
	@JsonProperty("productName")
	private String productName;
	@Column(name = "product_Id")
	@JsonProperty("productId")
	private String productId;
	@Column(name = "createdOn")
	@JsonProperty("createdOn")
	private LocalDate createdOn;
	@Column(name = "productInfo")
	@JsonProperty("productInfo")
	private String productInfo;

	@JsonProperty("customers")
	@JsonIgnoreProperties("products")
	@ManyToMany(targetEntity = Customer.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Customer> customers;

}
