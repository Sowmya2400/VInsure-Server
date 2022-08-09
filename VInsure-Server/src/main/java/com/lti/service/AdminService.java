package com.lti.service;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Claims;
import com.lti.entity.Customer;
import com.lti.entity.Policy;

public interface AdminService {

	String registerNewAdmin(Admin admin);

	Admin loginAdmin(int agentId, String password);

	List<Customer> viewAllCustomers();

	List<Claims> viewAllClaims();

	Claims findClaimsByClaimId(int claimId);

	Policy updatePolicyByPolicyNo(int policyNo);

	public Customer findCustomerByClaimId(int claimId);

	boolean approveOrReject(int claimId);

	boolean verifyAdmin(int adminId, String password);

	Customer getCustomerByEmail(String email);
}
