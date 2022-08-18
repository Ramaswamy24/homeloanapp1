package com.HomeLoanApp.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="emi")
public class Emi {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emi_id")
	private Long emiId;
	
	@NotNull
	@Column(name="due_date",nullable=false)
	private LocalDate dueDate;
	
	@NotNull
	@Column(name="emi_amount",nullable=false)
	private double emiAmount;
	
	@NotNull
	@Column(name="loan_amount",nullable=false)
	private double loanAmount;
	
	@NotNull
	@Column(name="interest_amount",nullable=false)
	private double interestAmount;
	
	@OneToOne
	private LoanAgreement loanAgreement;
	
	public Long getEmiId() {
		return emiId;
	}
	public void setEmiId(Long emiId) {
		this.emiId = emiId;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public double getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}
	public LoanAgreement getLoanAgreement() {
		return loanAgreement;
	}
	public void setLoanAgreement(LoanAgreement loanAgreement) {
		this.loanAgreement = loanAgreement;
	}
	
	@Override
	public String toString() {
		return "Emi [emiId=" + emiId + ", dueDate=" + dueDate + ", emiAmount=" + emiAmount + ", loanAmount="
				+ loanAmount + ", interestAmount=" + interestAmount + ", loanAgreement=" + loanAgreement + "]";
	}
}
