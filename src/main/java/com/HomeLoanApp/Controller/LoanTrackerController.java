package com.HomeLoanApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.LoanApplication;
import com.HomeLoanApp.Model.Status;
import com.HomeLoanApp.Service.LoanApplicationServiceImpl;
import com.HomeLoanApp.Service.UserServiceImpl;

@RestController
@RequestMapping("loantracker")
public class LoanTrackerController {
	
	@Autowired
	private LoanApplicationServiceImpl las;
	
	@Autowired
	private UserServiceImpl usi;
	
	@GetMapping("trackStatus/{userId}")
	public Status loanTracker(@PathVariable("userId") int userId) {
		usi.findUserWithId(userId);
		if(las.getLoanApplicationWithCustId(userId)!=null) {
			return las.getLoanApplicationWithCustId(userId).getStatus();
		}
		throw new EmptyInputException("237","Customer doesn't have Loan Application");
	}
	
	@GetMapping("track/{userId}")
	public List<LoanApplication> getAllLoanStatus(@PathVariable("userId") int userId){
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("customer")) {
			return las.getAllLoanApplicationWithCustId(userId);
		}
		throw new EmptyInputException("230","This feature is only accessible for Customer only");
	}
}
