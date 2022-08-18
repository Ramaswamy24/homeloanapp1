package com.HomeLoanApp.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="customerdata")
public class Customer{
	
	@OneToOne
	private User user;
	
	@NotNull(message="The value should not be null")
	@Length(min=2,max=120)
	@Pattern(regexp="^[A-Za-z]*$",message="Should be only characters")
	@Column(name="customer_name")
	private String customerName;
	
	@NotNull(message="The value should not be null")
	@Length(min=10,max=10,message="The Length should be exactly 10 digits")
	@Pattern(regexp="[0-9]+",message="Please Enter Numerical digits only")
	@Column(name="mobile_number",unique=true)
	private String mobileNumber;
	
	@NotNull(message="The value should not be null")
	@NotBlank(message="Email should not be blank")
	@Email(message="Should be in a Correct Format e.g.(abc@xyz.com)",regexp="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
	@Column(name="email_id",unique=true,length=120)
	private String emailId;
	
	@NotNull(message="The value should not be null")
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	
	@NotNull(message="The value should not be null")
	@NotBlank
	@Pattern(regexp="^[A-Za-z]*$",message="Should be only characters")
	@Column(name="gender")
	private String gender;
	
	@NotNull(message="The value should not be null")
	@NotBlank
	@Pattern(regexp="^[A-Za-z]*$",message="Should be only characters")
	@Column(name="nationality")
	private String nationality;
	
	@Id
	@NotNull(message="The value should not be null")
	@NotBlank
	@Length(min=12,max=12,message="Length should be exactly 12 Digits")
	@Pattern(regexp="[0-9]+",message="Please Enter Numerical digits only")
	@Column(name="aadhar_number",unique=true)
	private String aadharNumber;
	
	@NotNull(message="The value should not be null")
	@NotBlank
	@Length(min=10,max=10,message="Length should be exactly 10 Alpha-Numerical characters")
	@Pattern(regexp="^[A-Z0-9]*$",message="Please Enter Alpha-Numerical values only")
	@Column(name="pan_number",unique=true)
	private String panNumber;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Customer [user=" + user + ", customerName=" + customerName + ", mobileNumber=" + mobileNumber
				+ ", emailId=" + emailId + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", nationality="
				+ nationality + ", aadharNumber=" + aadharNumber + ", panNumber=" + panNumber + "]";
	}
}
