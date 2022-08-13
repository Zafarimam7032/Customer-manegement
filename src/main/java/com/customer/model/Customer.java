package com.customer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "customer_Name")
	@JsonProperty("customerName")
	private String customerName;
	@Column(name = "mobile_Number")
	@JsonProperty("mobileNumber")
	private String mobileNumber;
	@Column(name = "customer_address")
	@JsonProperty("address")
	private String address;
	@Column(name = "customer_Id")
	@JsonProperty("customerId")
	private String customerId;
	
	@JsonProperty("products")
	@JsonIgnoreProperties("customers")
	@ManyToMany(targetEntity = Product.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Product> products;
	
	
}
