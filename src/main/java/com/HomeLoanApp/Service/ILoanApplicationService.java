package com.HomeLoanApp.Service;

import java.util.List;
import com.HomeLoanApp.Model.Status;
import com.HomeLoanApp.Model.LoanApplication;

public interface ILoanApplicationService {
	public LoanApplication deleteLoanApplicationById(long loanApplicationId);
	public LoanApplication retrieveLoanApplicationById(long loanApplicationId);
	public LoanApplication addLoanApplication(LoanApplication loanApplicationId);
	public LoanApplication updateLoanApplication(LoanApplication loanApplicationId);
	public List<LoanApplication> retrieveAllLoanApplications();
	public List<LoanApplication> retrieveLoanApplicationByStatus(Status status);
	public LoanApplication getLoanApplicationWithCustId(int custId);
	public LoanApplication getLoanApplicationWithCustomerId(int custId);
}
