package com.HomeLoanApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HomeLoanApp.Model.LoanApplication;
import com.HomeLoanApp.Model.Status;

public interface ILoanApplicationRepository extends JpaRepository<LoanApplication,Long>{
	public List<LoanApplication> findByStatus(Status status);
}
