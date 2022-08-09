package com.lti.service;

import java.util.List;

import com.lti.entity.Claims;
import com.lti.entity.Customer;

import com.lti.entity.Insurance;
import com.lti.entity.Otp;
import com.lti.entity.Policy;

public interface CustomerService {
	String registerNewCustomer(Customer customer);

	Customer loginCustomer(String email);

	Customer updateCustomer(Customer customer);

	Otp addOtp(Otp o);

	boolean checkOtp(String email, int otp);

	// dashboard
	List<Policy> viewAllPolicies(String email);

	public boolean verifyCustomer(String email, String password);

	// Adding Claims
	public boolean addAClaim(Claims claim);

	// AddingPolicy
	public Policy addAPolicyToAnExistingCustomer(Policy policy, Customer customer);

	public int findInsuranceIdByInsuranceTypeAndPlan(Insurance insurance);

	public Insurance findInsuranceByInsuranceId(int insuranceId);

	public Customer findCustomerByCustomerId(int customerId);

	public Insurance addAnInsurance(Insurance insurance);

	Customer getCustomerByEmail(String email);

	// Renew Policy
	public Policy renewPolicy(int policyNo);

	public Policy findPolicyByPolicyNo(int policyNo);

}
