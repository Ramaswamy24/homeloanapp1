package com.HomeLoanApp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.LandVerificationOfficer;
import com.HomeLoanApp.Model.LoanApplication;
import com.HomeLoanApp.Model.Status;
import com.HomeLoanApp.dao.ILandVerificationRepository;


@Service
public class LandVerificationOfficerServiceImpl implements ILandVerificationOfficerService{
	
	@Autowired
	private ILandVerificationRepository ilv;
	
	@Autowired
	private LoanApplicationServiceImpl las;
	
	@Override
	public LandVerificationOfficer addLand(LandVerificationOfficer land) {
		if(land.getOfficerContact().isEmpty()||land.getOfficerName().isEmpty()) {
			throw new EmptyInputException("200","Wrong input");
		}
		
		List<LandVerificationOfficer> l1=getAllLand();
		
		for(LandVerificationOfficer l:l1) {
			if(l.getOfficerContact().equals(land.getOfficerContact())) {
				throw new EmptyInputException("204","Land verification officer data already exists");
			}
		}
		
		return ilv.save(land);
	}
	
	@Override
	public List<LandVerificationOfficer> getAllLand(){
		return ilv.findAll();
	}

	@Override
	public void updateStatus(LoanApplication loanApplication) {
		List<LoanApplication> l1=las.retrieveAllLoanApplications();
		
		for(LoanApplication l:l1) {
			if(l.getApplicationId()==loanApplication.getApplicationId()) {
				loanApplication.setStatus(Status.WAITING_FOR_FINANCE_APPROVAL);
				las.updateLoanApplication(loanApplication);
				return;
			}
		}
		throw new EmptyInputException("206","Loan Application doesn't exist");
	}

	@Override
	public List<LoanApplication> getLoanApplicationByStatus() {
		return las.retrieveAllLoanApplications().stream().filter(loanApp->loanApp.isLandVerificationApproval()==false).collect(Collectors.toList());
	}
	
	public LandVerificationOfficer getLandOfficer(long landId) {
		List<LandVerificationOfficer> l1=getAllLand();
		
		for(LandVerificationOfficer lv:l1) {
			if(lv.getUser().getUserId()==landId) {
				return lv;
			}
		}
		throw new EmptyInputException("235","The Land officer doesn't exist");
	}
	
	
}
