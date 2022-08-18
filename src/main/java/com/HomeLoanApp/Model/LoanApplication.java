package com.HomeLoanApp.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="loanapplication")
public class LoanApplication{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="application_id")
	private long applicationId;
	
	@NotNull(message="The value should not be null")
	@Column(name="application_date")
	private LocalDate applicationDate;
	
	@DecimalMin(value = "45000")
	@NotNull(message="The value should not be null")
	@Column(name="loan_applied_amount")
	private double loanAppliedAmount;
	
	@DecimalMin(value = "45000")
	@NotNull(message="The value should not be null")
	@Column(name="loan_approved_amount")
	private double loanApprovedAmount;
	
	@Column(name="land_verification_approval")
	private boolean landVerificationApproval;
	
	@Column(name="finance_verification_approval")
	private boolean financeVerificationApproval;
	
	@Column(name="admin_approval")
	private boolean adminApproval;
	
	@NotNull(message="The value should not be null")
	@Column(name="status")
	private Status status;
	
	@Column(name="customer_id")
	private int customerId;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}
	public LocalDate getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}
	public double getLoanAppliedAmount() {
		return loanAppliedAmount;
	}
	public void setLoanAppliedAmount(double loanAppliedAmount) {
		this.loanAppliedAmount = loanAppliedAmount;
	}
	public double getLoanApprovedAmount() {
		return loanApprovedAmount;
	}
	public void setLoanApprovedAmount(double loanApprovedAmount) {
		this.loanApprovedAmount = loanApprovedAmount;
	}
	public boolean isLandVerificationApproval() {
		return landVerificationApproval;
	}
	public void setLandVerificationApproval(boolean landVerificationApproval) {
		this.landVerificationApproval = landVerificationApproval;
	}
	public boolean isFinanceVerificationApproval() {
		return financeVerificationApproval;
	}
	public void setFinanceVerificationApproval(boolean financeVerificationApproval) {
		this.financeVerificationApproval = financeVerificationApproval;
	}
	public boolean isAdminApproval() {
		return adminApproval;
	}
	public void setAdminApproval(boolean adminApproval) {
		this.adminApproval = adminApproval;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "LoanApplication [applicationId=" + applicationId + ", applicationDate=" + applicationDate
				+ ", loanAppliedAmount=" + loanAppliedAmount + ", loanApprovedAmount=" + loanApprovedAmount
				+ ", landVerificationApproval=" + landVerificationApproval + ", financeVerificationApproval="
				+ financeVerificationApproval + ", adminApproval=" + adminApproval + ", status=" + status
				+ ", customerId=" + customerId + "]";
	}
	
}
