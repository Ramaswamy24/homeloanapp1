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
@Table(name="admindata")
public class Admin{
	
	@OneToOne
	private User user;
	
	@NotNull(message="The value should not be null")
	@Length(min=2,max=120)
	@Pattern(regexp="^[A-Za-z]*$",message="Should be only characters")
	@Column(name="admin_name",nullable=false)
	private String adminName;
	
	@Id
	@NotNull(message="The value should not be null")
	@Length(min=10,max=10,message="The Length should be exactly 10 digits")
	@Pattern(regexp="[0-9]+",message="Please Enter Numerical digits only")
	@Column(name="admin_contact",unique=true)
	private String adminContact;
	
	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getAdminContact() {
		return adminContact;
	}
	
	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Admin [user=" + user + ", adminName=" + adminName + ", adminContact=" + adminContact + "]";
	}
	
}
