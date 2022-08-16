package com.customer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "customer_Name")
	@JsonProperty("customerName")
	@NotBlank
	@Size(max=15,min =4,message ="customer name should not be greater then 15 letter")
	private String customerName;
	@Column(name = "mobile_Number")
	@JsonProperty("mobileNumber")
	@NotBlank
	@Size(max = 12,min = 10,message = "digits should be under 12")
	private String mobileNumber;
	@Column(name = "customer_address")
	@JsonProperty("address")
	@NotBlank
	private String address;
	@Column(name = "customer_Id")
	@JsonProperty("customerId")
	@NotBlank
	@Size(max=10,min = 1,message = "customer id should not be greater then 10 ")
	private String customerId;
	@JsonProperty("products")
	@JsonIgnoreProperties("customers")
	@ManyToMany(targetEntity = Product.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Product> products;
	
	
}
