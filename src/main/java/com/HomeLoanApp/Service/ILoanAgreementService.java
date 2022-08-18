package com.HomeLoanApp.Service;

import java.util.List;

import com.HomeLoanApp.Model.LoanAgreement;

public interface ILoanAgreementService {
	public LoanAgreement deleteLoanAgreement(LoanAgreement loanAgreement);
	public List<LoanAgreement> retrieveAllLoanAgreement();
	public LoanAgreement retrieveLoanAgreementById(long loanAgreementId);
	public LoanAgreement addLoanAgreement(LoanAgreement loanAgreement);
	public LoanAgreement updateLoanAgreement(LoanAgreement loanAgreement);
	public LoanAgreement getLoanAgreementByCustomerId(int custId);
	public LoanAgreement getLoanAgreementWithApplicationId(long loanApplicationId);
	public LoanAgreement getLoanAgreementByCustId(int custId);
}
