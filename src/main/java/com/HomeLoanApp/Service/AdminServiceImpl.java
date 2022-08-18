package com.HomeLoanApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.Admin;
import com.HomeLoanApp.dao.IAdminRepository;

@Service
public class AdminServiceImpl implements IAdminService{
	
	@Autowired
	private IAdminRepository iar;
	
	@Override
	public Admin addAdmin(Admin admin) {
		
		if(admin.getAdminName().isEmpty()||admin.getAdminContact().isEmpty()) {
			throw new EmptyInputException("200","Wrong input");
		}
		
		List<Admin> l=getAllAdmin();
		for(Admin a1:l) {
			if(a1.getAdminContact().equals(admin.getAdminContact())) {
				throw new EmptyInputException("204","Admin Already exists");
			}
		}
		
		return iar.save(admin);
	}
	
	@Override
	public List<Admin> getAllAdmin(){
		return iar.findAll();
	}
	
	public Admin getAdmin(int adminId) {
		List<Admin> l1=getAllAdmin();
		
		for(Admin a1:l1) {
			if(a1.getUser().getUserId()==adminId) {
				return a1;
			}
		}
		
		throw new EmptyInputException("234","Admin doesn't exist");
	}
	
}
