package com.HomeLoanApp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.LoanAgreement;
import com.HomeLoanApp.Model.Status;
import com.HomeLoanApp.dao.ILoanAgreementRepository;

@Service
public class LoanAgreementServiceImpl implements ILoanAgreementService{
	
	@Autowired
	private ILoanAgreementRepository ila;
	
	@Autowired
	private LoanApplicationServiceImpl las;

	@Override
	public LoanAgreement deleteLoanAgreement(LoanAgreement loanAgreement) {
		ila.deleteById(loanAgreement.getLoanAgreementId());
		return null;
	}

	@Override
	public List<LoanAgreement> retrieveAllLoanAgreement() {
		return ila.findAll();
	}

	@Override
	public LoanAgreement retrieveLoanAgreementById(long loanAgreementId) {
		List<LoanAgreement> l1=retrieveAllLoanAgreement();
		System.out.println("LoanAgreemntId: "+loanAgreementId);
		for(LoanAgreement l:l1) {
			if(l.getLoanAgreementId()==loanAgreementId) {
				System.out.println("IN!!!!!");
				return l;
			}
		}
		throw new EmptyInputException("219","Loan Agreement doesn't exist");
	}

	@Override
	public LoanAgreement addLoanAgreement(LoanAgreement loanAgreement) {
		List<LoanAgreement> l1=retrieveAllLoanAgreement();
		boolean applicationStatusCheck=false;
		if(loanAgreement.getLoanApplication().getStatus().equals(Status.APPROVED)) {
			applicationStatusCheck=true;
		}
		
		if(!applicationStatusCheck) {
			throw new EmptyInputException("229","Loan Application mentioned in the Agreement haven't Approved yet");
		}
		for(LoanAgreement l:l1) {
			if(l.getLoanApplication().getApplicationId()==loanAgreement.getLoanApplication().getApplicationId()) {
				throw new EmptyInputException("224","The Loan Agreement with the Loan Application ID already exists");
			}
		}
		return ila.save(loanAgreement);
	}

	@Override
	public LoanAgreement updateLoanAgreement(LoanAgreement loanAgreement) {
		List<LoanAgreement> l1=retrieveAllLoanAgreement();
		long agreementId=0;
		boolean applicationStatusCheck=false;
		if(loanAgreement.getLoanApplication().getStatus().equals(Status.APPROVED)) {
			applicationStatusCheck = true;
		}
		
		if(!applicationStatusCheck) {
			throw new EmptyInputException("229","Loan Application mentioned in the Agreement haven't Approved yet");
		}
		for(LoanAgreement l:l1) {
			if(l.getLoanApplication().getApplicationId()==loanAgreement.getLoanApplication().getApplicationId()&&l.getLoanAgreementId()!=loanAgreement.getLoanAgreementId()) {
				throw new EmptyInputException("224","The Loan Agreement with the Loan Application ID already exists");
			}
			if(l.getLoanAgreementId()==loanAgreement.getLoanAgreementId()) {
				agreementId=l.getLoanAgreementId();
			}
		}
		if(agreementId!=0) {
			return ila.save(loanAgreement);
		}
		throw new EmptyInputException("219","Loan Agreement doesn't exist");
	}
	
	@Override 
	public LoanAgreement getLoanAgreementByCustId(int custId) {
		long loanApplicationId=las.getLoanApplicationWithCustId(custId).getApplicationId();
		List<LoanAgreement> l1=retrieveAllLoanAgreement();
		
		for(LoanAgreement l:l1) {
			if(l.getLoanApplication().getApplicationId()==loanApplicationId) {
				return l;
			}
		}
		throw new EmptyInputException("219","Loan Agreement doesn't exist");
	}
	
	@Override 
	public LoanAgreement getLoanAgreementByCustomerId(int custId) {
		long loanApplicationId=las.getLoanApplicationWithCustId(custId).getApplicationId();
		List<LoanAgreement> l1=retrieveAllLoanAgreement();
		
		for(LoanAgreement l:l1) {
			if(l.getLoanApplication().getApplicationId()==loanApplicationId) {
				return l;
			}
		}
		return null;
	}
	
	@Override
	public LoanAgreement getLoanAgreementWithApplicationId(long loanApplicationId) {
		List<LoanAgreement> l1=retrieveAllLoanAgreement().stream().filter(agreement->agreement.getLoanApplication().getApplicationId()==loanApplicationId).collect(Collectors.toList());
		if(!l1.isEmpty()) {
			return l1.get(0);
		}
		throw new EmptyInputException("219","Loan Agreement doesn't exist");
	}
}
