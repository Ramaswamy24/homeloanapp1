package com.HomeLoanApp.Classes;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class HomeLoanBorrowingAmountCalculator {
	
	@NotBlank
	@DecimalMin(value="45000")
	@NotNull(message="The value should not be null")
	private double loanAmount;
	
	@NotBlank
	@DecimalMin(value="0.1")
	@DecimalMax(value="100")
	@NotNull(message="The value should not be null")
	private double rateOfInterest;
	
	@NotBlank
	@Min(value=1)
	@Max(value=70)
	@NotNull(message="The value should not be null")
	private int tenure;
	
	@NotBlank
	@DecimalMin(value="30000")
	@NotNull(message="The value should not be null")
	private double totalAnnualIncome;
	
	@NotBlank
	@PositiveOrZero
	@NotNull(message="The value should not be null")
	private double monthlyExpenses;
	
	@NotBlank
	@PositiveOrZero
	@NotNull(message="The value should not be null")
	private double otherMonthlyExpenses;
	
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public double getTotalAnnualIncome() {
		return totalAnnualIncome;
	}
	public void setTotalAnnualIncome(double totalAnnualIncome) {
		this.totalAnnualIncome = totalAnnualIncome;
	}
	public double getMonthlyExpenses() {
		return monthlyExpenses;
	}
	public void setMonthlyExpenses(double monthlyExpenses) {
		this.monthlyExpenses = monthlyExpenses;
	}
	public double getOtherMonthlyExpenses() {
		return otherMonthlyExpenses;
	}
	public void setOtherMonthlyExpenses(double otherMonthlyExpenses) {
		this.otherMonthlyExpenses = otherMonthlyExpenses;
	}
	
	public double getHomeLoanBorrowingAmount() {
		double inHandAmount=(totalAnnualIncome/12)-(monthlyExpenses+otherMonthlyExpenses);
		EMICalculator e=new EMICalculator(loanAmount,rateOfInterest,tenure);
		double emi=e.getEMIAmount();
		rateOfInterest=rateOfInterest/12/100;
		tenure*=12;
		if(inHandAmount>emi) {
			return loanAmount;
		}
		return (inHandAmount*((Math.pow((1+rateOfInterest),(tenure)))-1))/(rateOfInterest*(Math.pow((1+rateOfInterest),tenure)));
	}
}
