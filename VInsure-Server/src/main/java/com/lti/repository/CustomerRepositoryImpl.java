package com.lti.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Claims;
import com.lti.entity.Customer;

import com.lti.entity.Insurance;
import com.lti.entity.Otp;
import com.lti.entity.Policy;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	@PersistenceContext
	EntityManager em;


	@Override
	@Transactional
	public boolean registerNewCustomer(Customer customer) {
		return em.merge(customer) != null ? true : false;
	}

	@Override
	public boolean findCustomerByEmail(String email) {
		return (Long) em.createQuery("Select count(c.id) from Customer c where c.email = :em").setParameter("em", email)
				.getSingleResult() == 1 ? true : false;
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return (Customer) em.createQuery("select c from Customer c where c.email = :em").setParameter("em", email)
				.getSingleResult();
	}

	public boolean verifyCustomer(String email, String password) {
		String jpql = "select count(c) from Customer c where c.email=:em and c.password=:pwd";
		Query query = em.createQuery(jpql);
		query.setParameter("em", email);
		query.setParameter("pwd", password);
		Long count = (Long) query.getSingleResult();

		return count == 1 ? true : false;

	}

	public List<Policy> viewAllPolicies(String email) {
		String jpql = "select p from Policy p where p.customer.email=:em";
		Query query = em.createQuery(jpql);
		query.setParameter("em", email);
		return query.getResultList();

	}

	// ADD A POLICY
	@Transactional
	public Policy addAPolicyToAnExistingCustomer(Policy policy) {
		return em.merge(policy);
	}

	public int findInsuranceIdByInsuranceTypeAndPlan(Insurance insurance) {
		String jpql = "select i from Insurance i where i.type=:ty and i.plan=:pl";
		TypedQuery<Insurance> query = em.createQuery(jpql, Insurance.class);
		query.setParameter("ty", insurance.getType());
		query.setParameter("pl", insurance.getPlan());
		return query.getSingleResult().getInsuranceId();
	}

	public Insurance findInsuranceByInsuranceId(int insuranceId) {
		return em.find(Insurance.class, insuranceId);
	}

	public Customer findCustomerByCustomerId(int customerId) {
		return em.find(Customer.class, customerId);

	}

	@Override

	public List<Customer> viewAllCustomer() {

		String jpql = "select c from Customer c";

		TypedQuery<Customer> query = em.createQuery(jpql, Customer.class);

		return query.getResultList();
	}

	@Transactional
	public Insurance addAnInsurance(Insurance insurance) {
		Insurance persistedInsurance = em.merge(insurance);
		return persistedInsurance;
	}

	@Override
	public List<Claims> viewAllClaims() {
		String jpql = "select c from Claims c";
		TypedQuery<Claims> query = em.createQuery(jpql, Claims.class);

		return query.getResultList();
	}

	// Renewal
	@Override
	@Transactional
	public Policy renewPolicy(int policyNo) {
		String jpql = "update Policy p set p.issuanceDate=:iDate,p.expiryDate=:eDate where p.policyNo=:pNo";
		Query query = em.createQuery(jpql);
		LocalDate issuanceDate = LocalDate.now();
		LocalDate expiryDate = issuanceDate.plusYears(1);
		query.setParameter("iDate", issuanceDate);
		query.setParameter("eDate", expiryDate);
		query.setParameter("pNo", policyNo);
		query.executeUpdate();
		return em.find(Policy.class, policyNo);
	}

	@Override
	public Customer findCustomerByPolicyNo(int policyNo) {
		Policy p = em.find(Policy.class, policyNo);
		return p.getCustomer();
	}

	@Override
	public Policy findPolicyByPolicyNo(int policyNo) {

		return em.find(Policy.class, policyNo);
	}

	@Override
	public List<Claims> findClaimsByPolicyNo(int policyNo) {

		return null;
	}

	@Override
	@Transactional
	public boolean addAClaim(Claims claim) {
		return em.merge(claim) != null ? true : false;
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer customer) {

		return em.merge(customer);
	}

	@Override
	@Transactional
	public boolean approveOrReject(int claimId) {

		try {

			Query query = em.createQuery("DELETE FROM Claims c WHERE c.claimId =:p");
			int deletedCount = query.setParameter("p", claimId).executeUpdate();

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	@Override
	public Customer findCustomerByClaimId(int claimId) {

		Claims c = em.find(Claims.class, claimId);
		return findCustomerByPolicyNo(c.getPolicy().getPolicyNo());
	}

	@Override
	@Transactional
	public Policy updatePolicyByPolicyNo(int policyNo) {

		Policy p = findPolicyByPolicyNo(policyNo);

		p.setApprovalStatus(null);

		return em.merge(p);
	}

	@Override
	public Claims findClaimsByClaimId(int claimId) {
		return em.find(Claims.class, claimId);
	}

	@Override
	@Transactional
	public Otp addOtp(Otp o) {
		return em.merge(o);
	}

	@Override
	public boolean checkOtp(String email, int otp) {
		String jpql = "select count(o) from Otp o where o.email=:em and o.otp=:o";
		Query query = em.createQuery(jpql);
		query.setParameter("em", email);
		query.setParameter("o", otp);
		Long count = (Long) query.getSingleResult();
		return count == 1 ? true : false;
	}

}
