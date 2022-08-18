package com.HomeLoanApp.Service;

import java.util.List;

import com.HomeLoanApp.Classes.EMICalculator;
import com.HomeLoanApp.Model.Emi;

public interface IEmiService {

	public double calculateEmi(EMICalculator emi);
	public Emi addEmi(EMICalculator emi, long agreementId);
	public Emi getEmi(long agreementId);
	public List<Emi> getAllEmi();
	public void deleteEmiById(long emiId);

}
