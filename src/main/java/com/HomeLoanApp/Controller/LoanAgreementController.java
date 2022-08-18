package com.HomeLoanApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.LoanAgreement;
import com.HomeLoanApp.Service.AdminServiceImpl;
import com.HomeLoanApp.Service.LoanAgreementServiceImpl;
import com.HomeLoanApp.Service.LoanApplicationServiceImpl;
import com.HomeLoanApp.Service.UserServiceImpl;

@RestController
@RequestMapping("agreement")
public class LoanAgreementController {
	
	@Autowired
	private UserServiceImpl usi;
	
	@Autowired
	private LoanAgreementServiceImpl las;
	
	@Autowired
	private LoanApplicationServiceImpl lai;
	
	@Autowired
	private AdminServiceImpl asi;
	
	@PostMapping("addAgreement/{userId}/{applicationId}")
	public LoanAgreement addLoanAgreement(@PathVariable("userId") int userId,@PathVariable("applicationId") long applicationId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			if(applicationId==0) {
				throw new EmptyInputException("200","Wrong input");
			}
			LoanAgreement l=new LoanAgreement();
			if(lai.getLoanApplicationById(applicationId)==null) {
				throw new EmptyInputException("228","Loan Application for the ID mentioned in the Agreement doesn't exist");
			}
			l.setLoanApplication(lai.retrieveLoanApplicationById(applicationId));
			return las.addLoanAgreement(l);
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@PutMapping("updateAgreement/{userId}/{agreementId}/{applicationId}")
	public LoanAgreement updateLoanAgreement(@PathVariable("userId") int userId,@PathVariable("agreementId") long agreementId,@PathVariable("applicationId") long applicationId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			if(applicationId==0) {
				throw new EmptyInputException("200","Wrong input");
			}
			if(lai.getLoanApplicationById(applicationId)==null) {
				throw new EmptyInputException("228","Loan Application for the ID mentioned in the Agreement doesn't exist");
			}
			return las.updateLoanAgreement(las.retrieveLoanAgreementById(agreementId));
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@DeleteMapping("deleteAgreement/{userId}/{agreementId}")
	public String deleteLoanAgreement(@PathVariable("userId") int userId,@PathVariable("agreementId") long agreementId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			if(las.deleteLoanAgreement(las.retrieveLoanAgreementById(agreementId))==null) {
				return "Deleted Successfully";
			}
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@GetMapping("getAllLoanAgreement/{userId}")
	public List<LoanAgreement> getAllLoanApplication(@PathVariable("userId") int userId){
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			return las.retrieveAllLoanAgreement();
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@GetMapping("getLoanAgreement/{userId}/{agreementId}")
	public LoanAgreement getLoanAgreement(@PathVariable("userId") int userId,@PathVariable("agreementId") long agreementId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			return las.retrieveLoanAgreementById(agreementId);
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
}
