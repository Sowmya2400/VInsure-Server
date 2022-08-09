package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vinsure_customer")
public class Customer {

	@Id
	@SequenceGenerator(name = "customerId_seq", initialValue = 10001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerId_seq")
	private int customerId;

	@Column(length = 20, nullable = false)
	private String name;

	@Column(length = 30, nullable = false)
	private String email;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@Column(length = 10, nullable = false)
	private String phone;

	@Column(length = 50, nullable = false)
	private String address;

	@Column(length = 20, nullable = false)
	private String password;

	private String aadharCard;
	private String panCard;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	List<Policy> policies;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAadharCard() {
		return aadharCard;
	}

	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}

}
