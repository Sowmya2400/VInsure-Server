package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Admin;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public boolean registerNewAdmin(Admin admin) {
		return em.merge(admin) != null ? true : false;
	}

	@Override
	public Admin loginAdmin(int agentId, String password) {

		return (Admin) em.createQuery("select a from Admin a where a.adminId = :em and a.password = :pw ")
				.setParameter("em", agentId).setParameter("pw", password).getSingleResult();

	}

	@Override
	public boolean findAdminById(int adminId) {

		return (long) em.createQuery("Select count(a.adminId) from Admin a where a.adminId = :em")
				.setParameter("em", adminId).getSingleResult() == 1 ? true : false;
	}

	@Override
	public boolean verifyAdmin(int adminId, String password) {

		return (long) em.createQuery("select count(a) from Admin a where a.adminId = : em and a.password=:pw")

				.setParameter("em", adminId).setParameter("pw", password).getSingleResult() == 1 ? true : false;
	}

}
