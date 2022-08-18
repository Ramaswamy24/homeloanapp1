package com.HomeLoanApp.Service;

import java.util.List;

import com.HomeLoanApp.Model.LandVerificationOfficer;
import com.HomeLoanApp.Model.LoanApplication;

public interface ILandVerificationOfficerService {
	public void updateStatus(LoanApplication loanApplication);
	public LandVerificationOfficer addLand(LandVerificationOfficer land);
	public List<LandVerificationOfficer> getAllLand();
	public List<LoanApplication> getLoanApplicationByStatus();
}
