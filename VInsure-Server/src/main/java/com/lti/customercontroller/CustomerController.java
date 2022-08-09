package com.lti.customercontroller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AddPolicyDto;
import com.lti.dto.CustomerUpdateDto;
import com.lti.dto.LoginDto;
import com.lti.dto.OtpDto;
import com.lti.dto.PolicyDto;
import com.lti.dto.policyLoginDto;
import com.lti.entity.ApprovalStatus;
import com.lti.entity.Claims;
import com.lti.entity.Customer;
import com.lti.entity.Insurance;
import com.lti.entity.InsuranceType;
import com.lti.entity.Otp;
import com.lti.entity.PlanType;
import com.lti.entity.Policy;
import com.lti.entity.PolicyPlan;
import com.lti.entity.Travel;
import com.lti.entity.TravelType;
import com.lti.entity.Vehicle;
import com.lti.entity.VehicleType;
import com.lti.service.CustomerService;
import com.lti.service.EmailService;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	private EmailService emailService;

	// donot delete
	@PostMapping(path = "/register/{otp}")
	Map<String, String> register(@RequestBody Customer customer, @PathVariable int otp) {
		HashMap<String, String> response = new HashMap<>();
		try {
			if (customerService.checkOtp(customer.getEmail(), otp)) {
				System.out.println("Registering Customer: " + customer.getName() + " " + customer.getCustomerId());
				if (customerService.registerNewCustomer(customer).equals("Already Registered")) {
					response.put("Response", "ALREADY REGISTERED");
				} else {
					response.put("Response", "OK");
				}
			} else {
				response.put("Response", "INCORRECT");
			}

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Registering Customer: " + customer.getName() + " failed!");
			response.put("Response", "FAILED");
			return response;
		}

	}

	@PostMapping(path = "/update-customer")
	Map<String, String> updateCustomer(@RequestBody CustomerUpdateDto customerUpdateDto) {
		HashMap<String, String> response = new HashMap<>();
		try {
			System.out.println(customerUpdateDto.getOldEmail() + " " + customerUpdateDto.getOldPassword());
			if (customerService.verifyCustomer(customerUpdateDto.getOldEmail(), customerUpdateDto.getOldPassword())) {
				Customer c = customerService.getCustomerByEmail(customerUpdateDto.getOldEmail());
				if (customerUpdateDto.getFullName() != null && !customerUpdateDto.getFullName().equals("")) {
					c.setName(customerUpdateDto.getFullName());
				}
				if (customerUpdateDto.getEmail() != null && !customerUpdateDto.getEmail().equals("")) {
					c.setEmail(customerUpdateDto.getEmail());
				}
				if (customerUpdateDto.getPassword() != null && !customerUpdateDto.getPassword().equals("")) {
					c.setPassword(customerUpdateDto.getPassword());
				}
				if (customerUpdateDto.getDateOfBirth() != null) {
					c.setDateOfBirth(customerUpdateDto.getDateOfBirth());
				}
				if (customerUpdateDto.getContactNumber() != null && !customerUpdateDto.getContactNumber().equals("")) {
					c.setPhone(customerUpdateDto.getContactNumber());
				}
				if (customerUpdateDto.getAddress() != null && !customerUpdateDto.getAddress().equals("")) {
					c.setAddress(customerUpdateDto.getAddress());
				}
				customerService.updateCustomer(c);
				emailService.sendEmailForNewRegistration(c.getEmail(), "Your details has been updated Successfully!",
						"Details Updation");
				response.put("Response", "OK");
			} else {
				response.put("Response", "INCORRECT");
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("Response", "FAILED");
			return response;
		}

	}

	// donot delete
	@PostMapping(path = "/login")
	Map<String, Object> login(@RequestBody LoginDto logindto) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			if (customerService.verifyCustomer(logindto.getEmail(), logindto.getPassword())) {
				Customer c = customerService.getCustomerByEmail(logindto.getEmail());
				System.out.println(c.getName() + " logged in!");
				response.put("name", c.getName());
				response.put("email", c.getEmail());
				response.put("dateOfBirth", c.getDateOfBirth().toString());
				response.put("customerId", c.getCustomerId());
				response.put("Response", "OK");
			} else {
				response.put("Response", "INCORRECT");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("Response", "FAILED");
		}
		return response;
	}

	@PostMapping(path = "/otp/{email}")
	Map<String, Object> sendOtp(@PathVariable("email") String email) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			Random rand = new Random();
			int otp = 10000 + rand.nextInt(9999);
			emailService.sendEmailForNewRegistration(email, "You V Insure OTP password is - " + String.valueOf(otp),
					"V Insure OTP");
			Otp o = new Otp();
			o.setEmail(email);
			o.setOtp(otp);
			customerService.addOtp(o);
			response.put("Response", "OK");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("Response", "FAILED");
			return response;
		}

	}

	@PostMapping(path = "/check-otp")
	Map<String, Object> checkOtp(@RequestBody OtpDto otpDto) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			if (customerService.checkOtp(otpDto.getEmail(), otpDto.getOtp())) {
				Customer c = customerService.getCustomerByEmail(otpDto.getEmail());
				c.setPassword(otpDto.getPassword());
				customerService.updateCustomer(c);
				response.put("Response", "OK");
			} else {
				response.put("Response", "INCORRECT");
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("Response", "FAILED");
			return response;
		}

	}

	// donot delete
	@PostMapping(path = "/customer-policies")
	Map<String, Object> customerPolicies(@RequestBody LoginDto logindto) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			if (customerService.verifyCustomer(logindto.getEmail(), logindto.getPassword())) {
				List<Policy> P = customerService.viewAllPolicies(logindto.getEmail());
				if (P.size() == 0) {
					response.put("Response", "EMPTY");
				}
				List<Object> policies = new ArrayList<>();
				for (Policy p : P) {

					List<Object> t = new ArrayList<>();
					t.add(p.getPolicyNo());
					t.add(p.getPriceOfPremium());
					t.add(p.getPolicyDuration());
					t.add(p.getIssuanceDate());
					t.add(p.getApprovalStatus());
					t.add(p.getPolicyPlan());
					t.add(p.getMaxClaim());

					if (p.getTravel() != null) {
						t.add("Travel");
					} else if (p.getVehicle() != null) {
						t.add("Vehicle");
					}

					policies.add(t);

				}
				response.put("Policies", policies);
				response.put("Response", "OK");
			} else {
				response.put("Response", "FAILED");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("Response", "FAILED");
		}

		return response;
	}

	// donot delete
	@PostMapping(path = "/check-policy/{policyNo}")
	Map<String, Object> checkPolicy(@RequestBody LoginDto logindto, @PathVariable("policyNo") int policyNo) {
		HashMap<String, Object> response = new HashMap<>();
		try {
			if (customerService.verifyCustomer(logindto.getEmail(), logindto.getPassword())) {
				Policy p = customerService.findPolicyByPolicyNo(policyNo);
				if (p != null && p.getCustomer().getEmail().equals(logindto.getEmail())) {
					// max claim amount here

					response.put("MaxAmount", p.getMaxClaim());
					response.put("Response", "OK");
				} else {
					response.put("Response", "NOTFOUND");
				}

			} else {
				response.put("Response", "INCORRECT");
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("Response", "FAILED");
			return response;
		}

	}

	// donot delete
	@PostMapping(path = "/add-claim/{policyNo}/{reason}/{amount}")
	Map<String, String> addClaim(@RequestBody LoginDto logindto, @PathVariable("policyNo") int policyNo,
			@PathVariable("reason") String reason, @PathVariable("amount") int amount) {
		HashMap<String, String> response = new HashMap<>();
		try {
			if (customerService.verifyCustomer(logindto.getEmail(), logindto.getPassword())
					&& amount <= customerService.findPolicyByPolicyNo(policyNo).getMaxClaim()) {
				Claims c = new Claims();
				Policy p = customerService.findPolicyByPolicyNo(policyNo);
				c.setPolicy(p);
				c.setClaimReason(reason);
				c.setClaimAmount(amount);
				c.setClaimDate(java.time.LocalDate.now());
				p.setApprovalStatus(ApprovalStatus.PROCESSING);
				customerService.addAClaim(c);
				response.put("Response", "OK");
			} else {
				response.put("Response", "INCORRECT");
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("Response", "FAILED");
			return response;
		}

	}

	// donot delete
	@PostMapping(path = "/add-policy")
	Map<String, String> addPolicy(@RequestBody AddPolicyDto policyDto) {
		HashMap<String, String> response = new HashMap<>();
		try {

			if (customerService.verifyCustomer(policyDto.getEmail(), policyDto.getPassword())) {
				Policy p = new Policy();
				p.setIssuanceDate(LocalDate.now());
				p.setExpiryDate(LocalDate.now().plusYears(1));
				p.setApprovalStatus(null);
				if (policyDto.getPolicyPlan().equals("PLATINUM")) {
					p.setPolicyPlan(PolicyPlan.PLATINUM);
				} else if (policyDto.getPolicyPlan().equals("GOLD")) {
					p.setPolicyPlan(PolicyPlan.GOLD);
				} else if (policyDto.getPolicyPlan().equals("SILVER")) {
					p.setPolicyPlan(PolicyPlan.SILVER);
				}
				p.setPriceOfPremium(policyDto.getPriceOfPremium());
				p.setMaxClaim(policyDto.getMaxClaim());
				p.setPolicyDuration(policyDto.getPolicyDuration());

				if (policyDto.getInsuranceFor().equals("VEHICLE")) {
					if (policyDto.getInsuranceType().equals("COMPREHENSIVE")) {
						p.setInsuranceType(InsuranceType.COMPREHENSIVE);
					} else if (policyDto.getInsuranceType().equals("THIRDPARTY")) {
						p.setInsuranceType(InsuranceType.THIRDPARTY);
					}
					p.setPolicyDuration(365);

					Vehicle v = new Vehicle();
					if (policyDto.getVehicleType().equals("TWOWHEELER")) {
						v.setVehicletype(VehicleType.TWOWHEELER);
					} else if (policyDto.getVehicleType().equals("FOURWHEELER")) {
						v.setVehicletype(VehicleType.FOURWHEELER);
					}

					v.setVehicleCompany(policyDto.getVehicleCompany());
					v.setVehicleModel(policyDto.getVehicleModel());
					v.setPurchaseDate(policyDto.getPurchaseDate());
					v.setLicenseNo(policyDto.getLicenseNo());
					v.setRegistrationNo(policyDto.getRegistrationNo());
					v.setRegistrationFile(policyDto.getRegistrationFile());
					v.setEngineNo(policyDto.getEngineNo());
					v.setChassisNo(policyDto.getChassisNo());
					v.setPrice(policyDto.getPrice());
					v.setPolicy(p);
					p.setVehicle(v);
				} else if (policyDto.getInsuranceFor().equals("TRAVEL")) {
					Travel t = new Travel();
					t.setDepartureDate(policyDto.getDepartureDate());
					t.setReturnDate(policyDto.getReturnDate());
					t.setPlaceOfVisit(policyDto.getPlaceOfVisit());
					t.setNumberOfPeople(policyDto.getNumberOfPeople());
					t.setRegistrationFile(policyDto.getRegistrationFile());
					t.setPolicy(p);
					p.setTravel(t);
				}
				Customer c = customerService.getCustomerByEmail(policyDto.getEmail());
				p.setCustomer(c);
				customerService.addAPolicyToAnExistingCustomer(p, c);
				response.put("Response", "OK");

			} else {
				response.put("Response", "INCORRECT");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("Response", "FAILED");
		}
		return response;
	}

	// Renewal
	@PostMapping(value = "/renew/{policyNo}")
	// http://localhost:8585/vinsure/renew/{policyNo}
	public Map<String, Object> renewPolicy(@RequestBody LoginDto logindto, @PathVariable("policyNo") int policyNo) {
		HashMap<String, Object> response = new HashMap<>();
		if (customerService.verifyCustomer(logindto.getEmail(), logindto.getPassword())) {
			Policy p = customerService.renewPolicy(policyNo);
			if (p != null) {
				response.put("Response", "Success");
				response.put("Premium_Price", p.getPriceOfPremium());
			} else
				response.put("Response", "Failure");
		} else
			response.put("Response", "Your login details are wrong");
		System.out.println("Done");
		return response;
	}

	@PostMapping(value = "/policyDetails/{policyNo}")
	// http://localhost:8585/vinsure/policyDetails
	public Map<String, Object> policyDetails(@RequestBody LoginDto logindto, @PathVariable("policyNo") int policyNo) {
		HashMap<String, Object> response = new HashMap<>();
		if (customerService.verifyCustomer(logindto.getEmail(), logindto.getPassword())) {
			System.out.println(policyNo);
//			Policy p=customerService.findPolicyByPolicyNo(dto.policyDto.getPolicyNo());
			Policy p = customerService.findPolicyByPolicyNo(policyNo);
			if (p != null) {
				response.put("Policy_No", p.getPolicyNo());
				response.put("Price", p.getPriceOfPremium());
				response.put("Vehicle_Company", p.getVehicle().getVehicleCompany());
				response.put("Vehicle_Model", p.getVehicle().getVehicleModel());
				response.put("Registration_Number", p.getVehicle().getRegistrationNo());
				response.put("License_No", p.getVehicle().getLicenseNo());
				response.put("Response", "Details Fetched");
				System.out.println(p.getExpiryDate());
				if (p.getExpiryDate().isAfter(LocalDate.now())) {
					response.put("Expiry_Status", "Not Expired");

				} else {
					response.put("Expiry_Status", "Expired");
				}
			} else {
				response.put("Response", "No policy with the policy No");
			}
		} else
			response.put("Response", "Your login details are wrong");
		return response;
	}

}
	