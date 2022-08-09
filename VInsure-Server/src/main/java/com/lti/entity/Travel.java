package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vinsure_travel")
public class Travel {

	@Id
	@SequenceGenerator(name = "travel_seq", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "travel_seq", strategy = GenerationType.SEQUENCE)
	private int travelId;

	private LocalDate departureDate;
	private LocalDate returnDate;
	private String placeOfVisit;
	private int numberOfPeople;
	
	@Lob
	private String registrationFile;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="policyNo")
	Policy policy;
	

	public int getTravelId() {
		return travelId;
	}

	public void setTravelId(int travelId) {
		this.travelId = travelId;
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

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}


	public String getRegistrationFile() {
		return registrationFile;
	}


	public void setRegistrationFile(String registrationFile) {
		this.registrationFile = registrationFile;
	}
	
	
	
	
	
	
}
