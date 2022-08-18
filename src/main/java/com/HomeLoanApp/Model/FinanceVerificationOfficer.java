package com.HomeLoanApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="financeofficerdata")
public class FinanceVerificationOfficer{
	
	@OneToOne
	private User user;
	
	@NotNull(message="The value should not be null")
	@Length(min=2,max=120)
	@Pattern(regexp="^[A-Za-z]*$",message="Should be only characters")
	@Column(name="fin_officer_name")
	private String finOfficerName;
	
	@Id
	@NotNull(message="The value should not be null")
	@Length(min=10,max=10,message="The Length should be exactly 10 digits")
	@Pattern(regexp="[0-9]+",message="Please Enter Numerical digits only")
	@Column(name="fin_officer_contact",unique=true)
	private String finOfficerContact;
	
	
	public String getFinOfficerName() {
		return finOfficerName;
	}
	
	public void setFinOfficerName(String finOfficerName) {
		this.finOfficerName = finOfficerName;
	}
	
	public String getFinOfficerContact() {
		return finOfficerContact;
	}
	
	public void setFinOfficerContact(String finOfficerContact) {
		this.finOfficerContact = finOfficerContact;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "FinanceVerificationOfficer [user=" + user + ", finOfficerName=" + finOfficerName
				+ ", finOfficerContact=" + finOfficerContact + "]";
	}

}
