package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Claims;
import com.lti.entity.Customer;
import com.lti.entity.Insurance;
import com.lti.entity.Otp;
import com.lti.entity.Policy;
import com.lti.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmailService emailService;
	


	@Override
	public String registerNewCustomer(Customer customer) {
		if (!customerRepository.findCustomerByEmail(customer.getEmail())) {
			customerRepository.registerNewCustomer(customer);
			// Send Email
			emailService.sendEmailForNewRegistration(customer.getEmail(),
					"Congratulations, You have been successfully registered on V Insure. Explore and choose any insurance plan suitable to you. All the best!",
					"Registration Confirmation");
			return "Ok";
		} else {
			System.out.println("Customer " + customer.getName() + " is Already Present!");
			return "Already Registered";
		}
	}

	@Override
	public Customer loginCustomer(String email) {
		return customerRepository.getCustomerByEmail(email);
	}

	@Override
	public List<Policy> viewAllPolicies(String email) {
		return customerRepository.viewAllPolicies(email);
	}

	@Override
	public boolean verifyCustomer(String email, String password) {
		return customerRepository.verifyCustomer(email, password);

	}

	@Override
	public Policy addAPolicyToAnExistingCustomer(Policy policy, Customer customer) {
		Policy p = customerRepository.addAPolicyToAnExistingCustomer(policy);
		emailService
				.sendEmailForNewRegistration(
						customer.getEmail(), "Congratulations! Your policy " + String.valueOf(p.getPolicyNo())
								+ " with " + p.getPolicyPlan() + " has become active from today.",
						"Policy Purchase Confirmation");
		return p;
	}

	@Override
	public int findInsuranceIdByInsuranceTypeAndPlan(Insurance insurance) {
		return customerRepository.findInsuranceIdByInsuranceTypeAndPlan(insurance);
	}

	@Override
	public Insurance findInsuranceByInsuranceId(int insuranceId) {
		return customerRepository.findInsuranceByInsuranceId(insuranceId);
	}

	@Override
	public Customer findCustomerByCustomerId(int customerId) {
		return customerRepository.findCustomerByCustomerId(customerId);
	}

	@Override
	public Insurance addAnInsurance(Insurance insurance) {
		return customerRepository.addAnInsurance(insurance);
	}

	// Renewal
	@Override
	public Policy renewPolicy(int policyNo) {
		Policy p = customerRepository.renewPolicy(policyNo);
		Customer customer = customerRepository.findCustomerByPolicyNo(policyNo);
		emailService.sendEmailForNewRegistration(customer.getEmail(), "Your policy has been renewed successfully",
				"Policy Renewal-Confirmation");
		return p;
	}

	@Override
	public Policy findPolicyByPolicyNo(int policyNo) {
		return customerRepository.findPolicyByPolicyNo(policyNo);
	}

	@Override
	public boolean addAClaim(Claims claim) {
		return customerRepository.addAClaim(claim);
	}

	@Override
	public Customer getCustomerByEmail(String email) {

		return customerRepository.getCustomerByEmail(email);
	}

	@Override
	public Customer updateCustomer(Customer customer) {

		return customerRepository.updateCustomer(customer);
	}

	@Override
	public Otp addOtp(Otp o) {

		return customerRepository.addOtp(o);
	}

	@Override
	public boolean checkOtp(String email, int otp) {
		return customerRepository.checkOtp(email, otp);
	}

}
