package com.HomeLoanApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HomeLoanApp.Model.LoanAgreement;

public interface ILoanAgreementRepository extends JpaRepository<LoanAgreement,Long>{

}
