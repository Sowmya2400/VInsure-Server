package com.lti.customercontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Admin;
import com.lti.entity.Claims;
import com.lti.entity.Customer;
import com.lti.service.AdminService;
import com.lti.service.EmailService;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	EmailService emailService;

//
	@PostMapping(path = "/admin-register")
	public String registerNewAdmin(@RequestBody Admin admin) {
		{

			adminService.registerNewAdmin(admin);

			return "You are now an admin";

		}

	}

	@PostMapping(path = "/admin-login")
	public Admin loginAdmin(@RequestBody Admin admin) {
		System.out.println(admin.getAdminId() + "  " + admin.getPassword());
		Admin a = adminService.loginAdmin(admin.getAdminId(), admin.getPassword());
		System.out.println(a.getAdminName() + "  " + "you are logged in as an admin");
		return a;
	}

	@PostMapping(path = "/customerlist")

	public Map<String, Object> viewAllCustomer(@RequestBody Admin admin) {
		HashMap<String, Object> response = new HashMap<>();
		if (adminService.verifyAdmin(admin.getAdminId(), admin.getPassword())) {
			List<Customer> customerList = adminService.viewAllCustomers();
			List<Object> customers = new ArrayList<>();
			for (Customer c : customerList) {
				List<Object> t = new ArrayList<>();
				t.add(c.getCustomerId());
				t.add(c.getName());
				t.add(c.getEmail());
				t.add(c.getPhone());
				t.add(c.getDateOfBirth());
				t.add(c.getPolicies().size());
				customers.add(t);
			}
			response.put("Response", "OK");
			response.put("Customers", customers);
		} else {
			response.put("Response", "INCORRECT");
		}
		return response;

	}

	@PostMapping(path = "/claimslist")

	public Map<String, Object> viewAllClaims(@RequestBody Admin admin) {

		HashMap<String, Object> response = new HashMap<>();

		if (adminService.verifyAdmin(admin.getAdminId(), admin.getPassword())) {

			List<Claims> claimsList = adminService.viewAllClaims();

			List<Object> claims = new ArrayList<>();

			for (Claims c : claimsList) {
				List<Object> t = new ArrayList<>();
				t.add(c.getClaimId());
				t.add(c.getPolicy().getPolicyNo());
				t.add(c.getClaimAmount());
				t.add(c.getClaimDate());
				t.add(c.getClaimReason());
				if(c.getPolicy().getTravel()!=null) {
					t.add(c.getPolicy().getTravel().getRegistrationFile());
				}
				else if(c.getPolicy().getVehicle()!=null) {
					t.add(c.getPolicy().getVehicle().getRegistrationFile());
				}
				
				claims.add(t);
			}

			response.put("Response", "OK");
			response.put("Claims", claims);
		} else {
			response.put("Response", "INCORRECT");
		}
		return response;

	}

	@PostMapping(path = "/decision/{claimId}/{dec}")
	public boolean approveOrReject(@RequestBody Admin admin, @PathVariable("dec") String dec,
			@PathVariable int claimId) {
		System.out.println(claimId);
		System.out.println(dec);
		try {
			Claims cm = adminService.findClaimsByClaimId(claimId);
			if (adminService.verifyAdmin(admin.getAdminId(), admin.getPassword())) {
				if (dec.equals("APPROVE")) {

					System.out.println("if else working");
					Customer c = adminService.findCustomerByClaimId(claimId);

					String customerEmail = c.getEmail();
					String customerName = c.getName();
					emailService.sendEmailForClaimApproved(customerEmail, "Dear " + customerName
							+ ", Your Claim with Policy No " + cm.getPolicy().getPolicyNo() + " has been approved! ",
							"Claim Approved");
					adminService.approveOrReject(claimId);

				} else if (dec.equals("REJECT")) {

					Customer c = adminService.findCustomerByClaimId(claimId);
					String customerEmail = c.getEmail();
					String customerName = c.getName();
					emailService.sendEmailForClaimReject(customerEmail, "Dear " + customerName
							+ ", Your Claim with Policy No " + cm.getPolicy().getPolicyNo() + " has been rejected! ",
							"Claim Rejected");
					adminService.approveOrReject(claimId);

				}

				adminService.updatePolicyByPolicyNo(cm.getPolicy().getPolicyNo());
			}

			return true;
		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

}
