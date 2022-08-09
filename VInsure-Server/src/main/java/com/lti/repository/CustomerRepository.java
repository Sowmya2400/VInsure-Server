package com.lti.repository;

import java.util.List;

import com.lti.entity.Claims;
import com.lti.entity.Customer;

import com.lti.entity.Insurance;
import com.lti.entity.Otp;
import com.lti.entity.Policy;

public interface CustomerRepository {
	boolean registerNewCustomer(Customer customer);

	boolean findCustomerByEmail(String email);

	Customer getCustomerByEmail(String email);

	List<Customer> viewAllCustomer();

	Customer updateCustomer(Customer customer);

	Otp addOtp(Otp o);

	boolean checkOtp(String email, int otp);

	// dashboard
	public List<Policy> viewAllPolicies(String email);

	public boolean verifyCustomer(String email, String password);

	public List<Claims> findClaimsByPolicyNo(int policyNo);

	// add a claim
	List<Claims> viewAllClaims();

	boolean addAClaim(Claims claim);

	Policy updatePolicyByPolicyNo(int policyNo);

	Claims findClaimsByClaimId(int claimId);

	boolean approveOrReject(int claimId);

	public Customer findCustomerByClaimId(int claimId);

	// add a policy
	public Policy addAPolicyToAnExistingCustomer(Policy policy);

	public int findInsuranceIdByInsuranceTypeAndPlan(Insurance insurance);

	public Insurance findInsuranceByInsuranceId(int insuranceId);

	public Customer findCustomerByCustomerId(int customerId);

	public Insurance addAnInsurance(Insurance insurance);

	// renew a policy;

	public Policy renewPolicy(int policyNo);

	public Customer findCustomerByPolicyNo(int policyNo);

	public Policy findPolicyByPolicyNo(int policyNo);

}
