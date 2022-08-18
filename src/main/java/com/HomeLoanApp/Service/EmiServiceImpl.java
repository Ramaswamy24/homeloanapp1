package com.HomeLoanApp.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Classes.EMICalculator;
import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.Emi;
import com.HomeLoanApp.dao.IEmiRepository;

@Service
public class EmiServiceImpl implements IEmiService{
	
	@Autowired
	private IEmiRepository ier;
	
	@Autowired
	private LoanAgreementServiceImpl lai;
	
	@Override
	public double calculateEmi(EMICalculator emi){
		if(emi.getTenure()==0||emi.getLoanAmount()==0||emi.getRateOfInterest()==0) {
			throw new EmptyInputException("200","Wrong input");
		}
		return emi.getEMIAmount();
	}
	
	@Override
	public Emi addEmi(EMICalculator emi,long agreementId) {
		Emi e=new Emi();
		e.setEmiAmount(calculateEmi(emi));
		LocalDate today=LocalDate.now();
		e.setDueDate(today.plusDays((long)(30-today.getDayOfMonth())));
		double out=((calculateEmi(emi)*(double)(emi.getTenure()*12))-emi.getLoanAmount())/(double)(emi.getTenure()*12);
		e.setInterestAmount(out);
		System.out.println("AgreementId "+agreementId);
		lai.retrieveLoanAgreementById(agreementId);
		if(getEmiByAgreementId(agreementId)!=null) {
			throw new EmptyInputException("236","Emi details with the Agreement ID is already exists");
		}
		e.setLoanAgreement(lai.retrieveLoanAgreementById(agreementId));
		e.setLoanAmount(emi.getLoanAmount());
		return ier.save(e);
	}
	
	@Override
	public Emi getEmi(long agreementId) {
		List<Emi> l1=getAllEmi();
		
		for(Emi e:l1) {
			if(e.getLoanAgreement().getLoanAgreementId()==agreementId) {
				return e;
			}
		}
		throw new EmptyInputException("217","Emi data not found");
	}
	
	@Override
	public List<Emi> getAllEmi(){
		return ier.findAll();
	}
	
	@Override
	public void deleteEmiById(long emiId) {
		List<Emi> l1=getAllEmi().stream().filter(emi->emi.getEmiId()==emiId).collect(Collectors.toList());
		if(!l1.isEmpty()) {
			ier.deleteById(emiId);
			return;
		}
		throw new EmptyInputException("217","Emi data not found");
	}
	
	public Emi getEmiByAgreementId(long agreementId) {
		List<Emi> l1=getAllEmi();
		
		for(Emi e:l1) {
			if(e.getLoanAgreement().getLoanAgreementId()==agreementId) {
				return e;
			}
		}
		return null;
	}
}
