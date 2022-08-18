package com.HomeLoanApp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.FinanceVerificationOfficer;
import com.HomeLoanApp.Model.LoanApplication;
import com.HomeLoanApp.Model.Status;
import com.HomeLoanApp.dao.IFinanceVerificationRepository;

@Service
public class FinanceVerificationOfficerServiceImpl implements IFinanceVerificationOfficerService{
	
	@Autowired
	private IFinanceVerificationRepository fvr;
	
	@Autowired
	private LoanApplicationServiceImpl las;
	
	@Override
	public FinanceVerificationOfficer addFinance(FinanceVerificationOfficer finance) {
		if(finance.getFinOfficerContact().isEmpty()||finance.getFinOfficerName().isEmpty()) {
			throw new EmptyInputException("200","Wrong input");
		}
		
		List<FinanceVerificationOfficer> l1=getAllFinance();
		
		for(FinanceVerificationOfficer f:l1) {
			if(f.getFinOfficerContact().equals(finance.getFinOfficerContact())) {
				throw new EmptyInputException("204","FinanceOfficer Already exists");
			}
		}
		
		return fvr.save(finance);
	}
	
	@Override
	public List<FinanceVerificationOfficer> getAllFinance(){
		return fvr.findAll();
	}

	@Override
	public void updateStatus(LoanApplication loanApplication) {
		List<LoanApplication> l1=las.retrieveAllLoanApplications();
		
		for(LoanApplication l:l1) {
			if(l.getApplicationId()==loanApplication.getApplicationId()) {
				if(loanApplication.isLandVerificationApproval()) {
					loanApplication.setStatus(Status.PENDING);
					las.updateLoanApplication(loanApplication);
					return;
				}
				throw new EmptyInputException("222","Land verification officer approval pending");
			}
		}
		throw new EmptyInputException("206","Loan Application doesn't exist");
	}

	@Override
	public List<LoanApplication> getLoanApplicationByFinanceStatus() {
		return las.retrieveAllLoanApplications().stream().filter(loanApp->loanApp.isFinanceVerificationApproval()==false&&loanApp.isLandVerificationApproval()==true).collect(Collectors.toList());
	}
	
	public FinanceVerificationOfficer getFinance(long financeId) {
		List<FinanceVerificationOfficer>l1=getAllFinance();
		
		for(FinanceVerificationOfficer f1:l1) {
			if(f1.getUser().getUserId()==financeId) {
				return f1;
			}
		}
		throw new EmptyInputException("236","The Finance officer doesn't exist");
	}
}
