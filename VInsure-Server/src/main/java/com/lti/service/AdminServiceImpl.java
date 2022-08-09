package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Admin;
import com.lti.entity.Claims;
import com.lti.entity.Customer;
import com.lti.entity.Policy;
import com.lti.repository.AdminRepository;
import com.lti.repository.CustomerRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public String registerNewAdmin(Admin admin) {
		if (!adminRepository.findAdminById(admin.getAdminId())) {
			adminRepository.registerNewAdmin(admin);

			return "You are now an Admin";
		} else {
			System.out.println(" " + admin.getAdminName() + " You are already an Admin");
			return "Already Registered";
		}

	}

	@Override
	public Admin loginAdmin(int agentId, String password) {
		// TODO Auto-generated method stub
		return adminRepository.loginAdmin(agentId, password);
	}

	@Override
	public List<Customer> viewAllCustomers() {

		return customerRepository.viewAllCustomer();
	}

	@Override
	public boolean verifyAdmin(int adminId, String password) {

		return adminRepository.verifyAdmin(adminId, password);
	}

	@Override
	public List<Claims> viewAllClaims() {

		return customerRepository.viewAllClaims();
	}

	@Override

	public boolean approveOrReject(int claimId) {

		return customerRepository.approveOrReject(claimId);
	}

	@Override
	public Customer findCustomerByClaimId(int claimId) {

		return customerRepository.findCustomerByClaimId(claimId);
	}

	public Customer getCustomerByEmail(String email) {

		return customerRepository.getCustomerByEmail(email);
	}

	@Override
	public Policy updatePolicyByPolicyNo(int policyNo) {

		return customerRepository.updatePolicyByPolicyNo(policyNo);
	}

	@Override
	public Claims findClaimsByClaimId(int claimId) {

		return customerRepository.findClaimsByClaimId(claimId);
	}

}
