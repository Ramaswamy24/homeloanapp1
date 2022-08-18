package com.HomeLoanApp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.LoanApplication;
import com.HomeLoanApp.Model.Status;
import com.HomeLoanApp.dao.ILoanApplicationRepository;

@Service
public class LoanApplicationServiceImpl implements ILoanApplicationService{
	
	@Autowired
	private ILoanApplicationRepository ilr;
	
	@Autowired
	private CustomerServiceImpl csi;

	@Override
	public LoanApplication deleteLoanApplicationById(long loanApplicationId) {
		
		List<LoanApplication> l1=retrieveAllLoanApplications();
		
		for(LoanApplication l:l1) {
			if(l.getApplicationId()==loanApplicationId) {
				ilr.deleteById(loanApplicationId);
				return new LoanApplication();
			}
		}
		throw new EmptyInputException("206","Loan Application doesn't exist");
	}

	@Override
	public LoanApplication retrieveLoanApplicationById(long loanApplicationId) {
		List<LoanApplication> l1=retrieveAllLoanApplications();
		
		for(LoanApplication l:l1) {
			if(l.getApplicationId()==loanApplicationId) {
				return l;
			}
		}
		throw new EmptyInputException("206","Loan Application doesn't exist");
	}

	@Override
	public LoanApplication addLoanApplication(LoanApplication loanApplication) {
		if(loanApplication.getLoanAppliedAmount()<=0||loanApplication.getLoanApprovedAmount()<=0) {
			throw new EmptyInputException("200","Wrong input");
		}
		return ilr.save(loanApplication);
	}

	@Override
	public LoanApplication updateLoanApplication(LoanApplication loanApplication) {
		long id=loanApplication.getApplicationId();
		
		List<LoanApplication> l1=retrieveAllLoanApplications();
		
		for(LoanApplication l:l1) {
			if(l.getApplicationId()==id) {
				loanApplication.setCustomerId(l.getCustomerId());
				return ilr.save(loanApplication);
			}
		}
		throw new EmptyInputException("206","Loan Application doesn't exist");
	}

	@Override
	public List<LoanApplication> retrieveAllLoanApplications() {
		return ilr.findAll();
	}
	
	@Override
	public List<LoanApplication> retrieveLoanApplicationByStatus(Status status){
		return ilr.findByStatus(status);
	}
	
	@Override
	public LoanApplication getLoanApplicationWithCustId(int custId) {
		List<LoanApplication> l1=retrieveAllLoanApplications();
		
		for(LoanApplication l:l1) {
			if(l.getCustomerId()==custId) {
				return l;
			}
		}
		throw new EmptyInputException("206","Loan Application doesn't exist");
	}
	
	@Override
	public LoanApplication getLoanApplicationWithCustomerId(int custId) {
		List<LoanApplication> l1=retrieveAllLoanApplications();
		
		for(LoanApplication l:l1) {
			if(l.getCustomerId()==custId) {
				return l;
			}
		}
		return null;
	}
	
	public LoanApplication getLoanApplicationById(long loanApplicationId) {
		List<LoanApplication> l1=retrieveAllLoanApplications();
		
		for(LoanApplication l:l1) {
			if(l.getApplicationId()==loanApplicationId) {
				return l;
			}
		}
		return null;
	}
	
	public List<LoanApplication> getAllLoanApplicationWithCustId(int custId) {
		List<LoanApplication> l1=retrieveAllLoanApplications();
		csi.viewCustomer(custId);
		return l1.stream().filter(application->application.getCustomerId()==custId).collect(Collectors.toList());
	}
}
