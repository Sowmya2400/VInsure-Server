package com.lti.repository;

import com.lti.entity.Admin;

public interface AdminRepository {

	boolean registerNewAdmin(Admin admin);

	boolean findAdminById(int adminId);

	Admin loginAdmin(int agentId, String password);

	boolean verifyAdmin(int adminId, String password);

}
