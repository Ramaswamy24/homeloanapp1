package com.HomeLoanApp.Service;

import java.util.List;

import com.HomeLoanApp.Model.Admin;

public interface IAdminService {

	public Admin addAdmin(Admin admin);

	public List<Admin> getAllAdmin();
}
