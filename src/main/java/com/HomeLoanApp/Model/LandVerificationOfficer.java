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
@Table(name="landofficerdata")
public class LandVerificationOfficer{
	
	@OneToOne
	private User user;
	
	@NotNull(message="The value should not be null")
	@Length(min=2,max=120)
	@Pattern(regexp="^[A-Za-z]*$",message="Should be only characters")
	@Column(name="officer_name")
	private String officerName;
	
	@Id
	@NotNull(message="The value should not be null")
	@Length(min=10,max=10,message="The Length should be exactly 10 digits")
	@Pattern(regexp="[0-9]+",message="Please Enter Numerical digits only")
	@Column(name="officer_contact",unique=true)
	private String officerContact;
	
	
	public String getOfficerName() {
		return officerName;
	}
	
	public void setOfficerName(String officerName) {
		this.officerName=officerName;
	}
	
	public String getOfficerContact() {
		return officerContact;
	}
	
	public void setOfficerContact(String officerContact) {
		this.officerContact=officerContact;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "LandVerificationOfficer [user=" + user + ", officerName=" + officerName + ", officerContact="
				+ officerContact + "]";
	}
	
}
