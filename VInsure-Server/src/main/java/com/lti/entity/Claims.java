package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.bytebuddy.asm.Advice.Local;

@Entity
@Table(name = "vinsure_claims")

public class Claims {

	@Id
	@SequenceGenerator(name = "claims_seq", initialValue = 100001, allocationSize = 1)
	@GeneratedValue(generator = "claims_seq", strategy = GenerationType.SEQUENCE)
	int claimId;
	LocalDate claimDate;
	String claimReason;
	int claimAmount;

	@OneToOne
	@JoinColumn(name = "policyNo")
	Policy policy;

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public LocalDate getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}

	public String getClaimReason() {
		return claimReason;
	}

	public void setClaimReason(String claimReason) {
		this.claimReason = claimReason;
	}

	public int getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(int claimAmount) {
		this.claimAmount = claimAmount;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

}
