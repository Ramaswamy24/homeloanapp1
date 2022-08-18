package com.HomeLoanApp.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.Customer;
import com.HomeLoanApp.Model.Emi;
import com.HomeLoanApp.Model.LoanAgreement;
import com.HomeLoanApp.Model.LoanApplication;
import com.HomeLoanApp.Service.CustomerServiceImpl;
import com.HomeLoanApp.Service.EmiServiceImpl;
import com.HomeLoanApp.Service.LoanAgreementServiceImpl;
import com.HomeLoanApp.Service.LoanApplicationServiceImpl;
import com.HomeLoanApp.Service.UserServiceImpl;

@RestController
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	private UserServiceImpl usi;
	
	@Autowired
	private LoanApplicationServiceImpl las;
	
	@Autowired
	private LoanAgreementServiceImpl lai;
	
	@Autowired
	private CustomerServiceImpl ics;
	
	@Autowired
	private EmiServiceImpl esi;
	
	@GetMapping("getLoanApplication/{userId}")
	public LoanApplication getApplication(@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("customer")) {
			if(ics.getCustomer(userId).getAadharNumber()==null) {
				throw new EmptyInputException("233","The customer data is invalid");
			}
			return las.getLoanApplicationWithCustId(userId);
		}
		throw new EmptyInputException("220","This module will only be accessed by the customer");
	}
	
	@GetMapping("getLoanAgreement/{userId}")
	public LoanAgreement getAgreement(@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("customer")) {
			if(ics.getCustomer(userId).getAadharNumber()==null) {
				throw new EmptyInputException("233","The customer data is invalid");
			}
			return lai.getLoanAgreementByCustomerId(userId);
		}
		throw new EmptyInputException("220","This module will only be accessed by the customer");
	}
	
	@GetMapping("getEmiDetails/{userId}")
	public Emi getEmi(@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("customer")) {
			if(ics.getCustomer(userId).getAadharNumber()==null) {
				throw new EmptyInputException("233","The customer data is invalid");
			}
			if(lai.getLoanAgreementByCustId(userId)!=null) {
				return esi.getEmi(lai.getLoanAgreementByCustomerId(userId).getLoanAgreementId());
			}
		}
		throw new EmptyInputException("220","This module will only be accessed by the customer");
	}
	
	@PutMapping("updateLoanApplication/{userId}")
	public LoanApplication updateLoanApplication(@Valid @RequestBody LoanApplication loanApplication,@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("customer")) {
			if(loanApplication.isAdminApproval()||loanApplication.isFinanceVerificationApproval()||loanApplication.isLandVerificationApproval()) {
				throw new EmptyInputException("207","This application can only be updated by the admin");
			}
			if(ics.getCustomer(userId).getAadharNumber()==null) {
				throw new EmptyInputException("233","The customer data is invalid");
			}
			return las.updateLoanApplication(loanApplication);
		}
		throw new EmptyInputException("220","This module will only be accessed by the customer");
	}
	
	@PutMapping("updateCustomer/{userId}")
	public Customer updateCustomer(@Valid @RequestBody Customer customer,@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("customer")) {
			if(ics.getCustomer(userId).getAadharNumber()==null) {
				throw new EmptyInputException("233","The customer data is invalid");
			}
			customer.setUser(usi.findUserWithId(userId));
			return ics.updateCustomer(customer);
		}
		throw new EmptyInputException("220","This module will only be accessed by the customer");
	}
	
	@DeleteMapping("deleteCustomer/{userId}")
	public String deleteCustomer(@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("customer")) {
			if(ics.getCustomer(userId).getAadharNumber()==null) {
				throw new EmptyInputException("233","The customer data is invalid");
			}
			ics.deleteCustomer(ics.viewCustomer(userId));
			return "Deleted Successfully";
		}
		throw new EmptyInputException("220","This module will only be accessed by the customer");
	}

}
