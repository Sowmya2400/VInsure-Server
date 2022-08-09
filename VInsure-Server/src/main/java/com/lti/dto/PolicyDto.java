package com.lti.dto;

import java.time.LocalDate;

import com.lti.entity.PlanType;
import com.lti.entity.PolicyPlan;

public class PolicyDto {
	private int policyNo;
	private String customerId;
	private int insuranceId;
	private int policyDuration;
	private double priceOfPremium;
	private LocalDate issuanceDate;
	private LocalDate expiryDate;
	private String policyPlan;
	private double maxClaim;
	private String registrationFile;
	
	public VehicleDto vehicleDto;
	
	public TravelDto travelDto;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getPolicyDuration() {
		return policyDuration;
	}
	public void setPolicyDuration(int policyDuration) {
		this.policyDuration = policyDuration;
	}
	public double getPriceOfPremium() {
		return priceOfPremium;
	}
	public void setPriceOfPremium(double priceOfPremium) {
		this.priceOfPremium = priceOfPremium;
	}
	
	public LocalDate getIssuanceDate() {
		return issuanceDate;
	}
	public void setIssuanceDate(LocalDate issuanceDate) {
		this.issuanceDate = issuanceDate;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}
	public String getPolicyPlan() {
		return policyPlan;
	}
	public void setPolicyPlan(String policyPlan) {
		this.policyPlan = policyPlan;
	}
	public int getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(int policyNo) {
		this.policyNo = policyNo;
	}
	public double getMaxClaim() {
		return maxClaim;
	}
	public void setMaxClaim(double maxClaim) {
		this.maxClaim = maxClaim;
	}
	public String getRegistrationFile() {
		return registrationFile;
	}
	public void setRegistrationFile(String registrationFile) {
		this.registrationFile = registrationFile;
	}
	
	
	
}
