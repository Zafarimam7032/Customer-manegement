package com.customer.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	@NotBlank
	@Size(max=15,min =4,message ="productName should not be greater then 15 Character")
	private String productName;
	@Column(name = "product_Id")
	@JsonProperty("productId")
	@NotBlank
	@Size(max=10,min =2,message ="productId should not be greater then 15 Character")
	private String productId;
	@Column(name = "createdOn")
	@JsonProperty("createdOn")
	private LocalDate createdOn;
	@Column(name = "productInfo")
	@JsonProperty("productInfo")
	@NotBlank
	@Size(max=15,min =5,message ="product info should not be greater then 15 Character and should not be lesser then 5")
	private String productInfo;

	@JsonProperty("customers")
	@JsonIgnoreProperties("products")
	@ManyToMany(targetEntity = Customer.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Customer> customers;

}
