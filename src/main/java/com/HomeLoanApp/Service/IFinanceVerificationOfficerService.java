package com.HomeLoanApp.Service;

import java.util.List;

import com.HomeLoanApp.Model.FinanceVerificationOfficer;
import com.HomeLoanApp.Model.LoanApplication;

public interface IFinanceVerificationOfficerService {
	public void updateStatus(LoanApplication loanApplication);
	public FinanceVerificationOfficer addFinance(FinanceVerificationOfficer finance);
	public List<FinanceVerificationOfficer> getAllFinance();
	public List<LoanApplication> getLoanApplicationByFinanceStatus();
}
