package com.HomeLoanApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HomeLoanApp.Model.Admin;

public interface IAdminRepository extends JpaRepository<Admin,String>{

}
