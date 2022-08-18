package com.HomeLoanApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HomeLoanApp.Model.FinanceVerificationOfficer;

public interface IFinanceVerificationRepository extends JpaRepository<FinanceVerificationOfficer,String>{

}
