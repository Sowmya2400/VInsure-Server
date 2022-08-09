package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vinsure_vehicle")

public class Vehicle {

	@Id
	@SequenceGenerator(name = "vehicle_seq", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "vehicle_seq", strategy = GenerationType.SEQUENCE)
	private int vehicleId;
	private VehicleType vehicleType; // 2 wheeler or 4 wheeler
	private String vehicleCompany; // Manufacturing Company
	private String vehicleModel; // like Dzire,Rapid,Alto etc
	private LocalDate purchaseDate;
	private String licenseNo;
	@Column(unique = true)
	private String registrationNo; // Registration from RTO, number plate
	private String engineNo;
	private String chassisNo;
	private double price;
	@Lob
	private String registrationFile;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "policyNo")
	Policy policy;

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicletype(VehicleType vehicleType) {
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

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRegistrationFile() {
		return registrationFile;
	}

	public void setRegistrationFile(String registrationFile) {
		this.registrationFile = registrationFile;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

}
