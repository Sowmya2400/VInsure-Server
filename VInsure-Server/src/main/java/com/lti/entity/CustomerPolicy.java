package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vinsure_customer_insurance")
public class CustomerPolicy {

	@Id
	@SequenceGenerator(name = "cust_ins_seq", initialValue = 100001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_ins_seq")
	int insuranceId;

}
