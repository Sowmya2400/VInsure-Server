package com.lti.dto;

import java.time.LocalDate;

import com.lti.entity.Policy;
import com.lti.entity.Travel;
import com.lti.entity.Vehicle;

import net.bytebuddy.asm.Advice.Local;

public class AddPolicyDto {
	private String email;
	private String password;

	private double priceOfPremium;
	private String policyPlan;
	private String insuranceType;
	private double maxClaim;
	private String insuranceFor;
	private int policyDuration;

	private String vehicleType;
	private String vehicleCompany;
	private String vehicleModel;
	private LocalDate purchaseDate;
	private String licenseNo;
	private String registrationNo;
	private String registrationFile;
	private String engineNo;
	private String chassisNo;
	private double price;

	private LocalDate departureDate;
	private LocalDate returnDate;
	private String placeOfVisit;
	private int numberOfPeople;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getPriceOfPremium() {
		return priceOfPremium;
	}

	public void setPriceOfPremium(double priceOfPremium) {
		this.priceOfPremium = priceOfPremium;
	}

	public String getPolicyPlan() {
		return policyPlan;
	}

	public void setPolicyPlan(String policyPlan) {
		this.policyPlan = policyPlan;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public double getMaxClaim() {
		return maxClaim;
	}

	public void setMaxClaim(double maxClaim) {
		this.maxClaim = maxClaim;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleCompany() {
		return vehicleCompany;
	}

	public void setVehicleCompany(String vehicleCompany) {
		this.vehicleCompany = vehicleCompany;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public String getPlaceOfVisit() {
		return placeOfVisit;
	}

	public void setPlaceOfVisit(String placeOfVisit) {
		this.placeOfVisit = placeOfVisit;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getInsuranceFor() {
		return insuranceFor;
	}

	public void setInsuranceFor(String insuranceFor) {
		this.insuranceFor = insuranceFor;
	}

	public int getPolicyDuration() {
		return policyDuration;
	}

	public void setPolicyDuration(int policyDuration) {
		this.policyDuration = policyDuration;
	}
	public String getRegistrationFile() {
		return registrationFile;
	}
	public void setRegistrationFile(String registrationFile) {
		this.registrationFile = registrationFile;
	}
	
	

}
