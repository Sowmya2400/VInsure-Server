package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vinsure_policy")
public class Policy {

	@Id
	@SequenceGenerator(name = "policy_seq", initialValue = 1000001, allocationSize = 1)
	@GeneratedValue(generator = "policy_seq", strategy = GenerationType.SEQUENCE)
	int policyNo;
	LocalDate issuanceDate;
	LocalDate expiryDate;
	ApprovalStatus approvalStatus;
	PolicyPlan policyPlan;
	InsuranceType insuranceType;

	int policyDuration = 365;
	double priceOfPremium;
	double maxClaim;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vehicleId")
	Vehicle vehicle;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "travelId")
	Travel travel;

	@ManyToOne
	@JoinColumn(name = "customerId")
	Customer customer;

	@OneToOne(mappedBy = "policy", cascade = CascadeType.ALL)
	Claims claims;

	public int getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(int policyNo) {
		this.policyNo = policyNo;
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Claims getClaims() {
		return claims;
	}

	public void setClaims(Claims claims) {
		this.claims = claims;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Travel getTravel() {
		return travel;
	}

	public void setTravel(Travel travel) {
		this.travel = travel;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public PolicyPlan getPolicyPlan() {
		return policyPlan;
	}

	public void setPolicyPlan(PolicyPlan policyPlan) {
		this.policyPlan = policyPlan;
	}

	public double getMaxClaim() {
		return maxClaim;
	}

	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	public void setMaxClaim(double maxClaim) {
		this.maxClaim = maxClaim;
	}

}
