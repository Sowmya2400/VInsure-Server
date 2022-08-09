package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vinsure_insurance")
public class Insurance {

	@Id
	@SequenceGenerator(name = "insuranceSeq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "insuranceSeq", strategy = GenerationType.SEQUENCE)
	private int insuranceId; // 1-Travel & Comprehensive, 2-Motor & Comprehensive, 3-Motor & 3rd Party
	private InsuranceType type; // travel or motor
	private PlanType plan; // comprehensive or third party

	public Insurance() {
		super();
	}

	public Insurance(InsuranceType type, PlanType plan) {
		super();
		this.type = type;
		this.plan = plan;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public InsuranceType getType() {
		return type;
	}

	public void setType(InsuranceType type) {
		this.type = type;
	}

	public PlanType getPlan() {
		return plan;
	}

	public void setPlan(PlanType plan) {
		this.plan = plan;
	}

}
