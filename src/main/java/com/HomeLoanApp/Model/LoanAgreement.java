package com.HomeLoanApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="loanagreement")
public class LoanAgreement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="loan_agreement_id")
	private long loanAgreementId;
	@OneToOne
	private LoanApplication loanApplication;
	
	public long getLoanAgreementId() {
		return loanAgreementId;
	}
	public void setLoanAgreementId(long loanAgreementId) {
		this.loanAgreementId = loanAgreementId;
	}
	public LoanApplication getLoanApplication() {
		return loanApplication;
	}
	public void setLoanApplication(LoanApplication loanApplication) {
		this.loanApplication = loanApplication;
	}
	
	@Override
	public String toString() {
		return "LoanAgreement [loanAgreementId=" + loanAgreementId + ", loanApplication=" + loanApplication + "]";
	}
}
