package com.HomeLoanApp.Controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.LoanApplication;
import com.HomeLoanApp.Model.Status;
import com.HomeLoanApp.Model.User;
import com.HomeLoanApp.Service.AdminServiceImpl;
import com.HomeLoanApp.Service.CustomerServiceImpl;
import com.HomeLoanApp.Service.FinanceVerificationOfficerServiceImpl;
import com.HomeLoanApp.Service.LandVerificationOfficerServiceImpl;
import com.HomeLoanApp.Service.LoanApplicationServiceImpl;
import com.HomeLoanApp.Service.UserServiceImpl;

@RestController
@RequestMapping("loan")
public class LoanApplicationController {
	
	@Autowired
	private LoanApplicationServiceImpl las;
	
	@Autowired
	private UserServiceImpl usi;
	
	@Autowired
	private LandVerificationOfficerServiceImpl lvs;
	
	@Autowired
	private FinanceVerificationOfficerServiceImpl fvs;
	
	@Autowired
	private CustomerServiceImpl csi;
	
	@Autowired
	private AdminServiceImpl asi;
	
	
	@GetMapping("getAllLoanApplicationByStatusAdmin/{userId}")
	public List<LoanApplication> getLoanApplicationByStatus(@RequestParam Status status,@PathVariable("userId") int userId){
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			return las.retrieveLoanApplicationByStatus(status);
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@PostMapping("addLoanApplicationAdmin/{adminId}/{customerId}")
	public LoanApplication addLoanApplication(@Valid @RequestBody LoanApplication loanapp,@PathVariable("adminId") int adminId,@PathVariable("customerId") int customerId) {
		User u=usi.findUserWithId(adminId);
		if(u.getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(adminId);
			csi.viewCustomer(customerId);
			if(loanapp.isAdminApproval()||loanapp.isFinanceVerificationApproval()||loanapp.isLandVerificationApproval()) {
				throw new EmptyInputException("207","This application can only be updated by the admin");
			}
			if(las.getLoanApplicationWithCustomerId(customerId)!=null) {
				throw new EmptyInputException("223","The application for the customer is already exists");
			}
			loanapp.setCustomerId(customerId);
			loanapp.setStatus(Status.WAITING_FOR_LAND_VERIFICATION_OFFICE_APPROVAL);
			return las.addLoanApplication(loanapp);
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@PutMapping("updateLoanApplicationAdmin/{userId}/{loanApplicationId}")
	public LoanApplication updateLoanApplication(@PathVariable("userId") int userId,@RequestParam Status status,@PathVariable("loanApplicationId") long loanApplicationId) {
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			LoanApplication loanapp=las.retrieveLoanApplicationById(loanApplicationId);
			if(loanapp.isLandVerificationApproval()) {
				if(loanapp.isFinanceVerificationApproval()) {
					loanapp.setAdminApproval(true);
					if((csi.viewCustomer(loanapp.getCustomerId()).getAadharNumber().isEmpty()||csi.viewCustomer(loanapp.getCustomerId()).getPanNumber().isEmpty())&&(!(status.equals(Status.REJECTED)))) {
						loanapp.setStatus(Status.DOCUMENTS_NOT_UPLOADED);
					}
					else {
						loanapp.setStatus(status);
					}
					return las.updateLoanApplication(loanapp);
				}
				throw new EmptyInputException("215","Finance verification officer approval pending");
			}
			throw new EmptyInputException("216","Finance and Land verification officer approval pending");
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@DeleteMapping("deleteLoanApplicationAdmin/{userId}/{loanApplicationId}")
	public String deleteLoanApplication(@PathVariable("userId") int userId,@PathVariable("loanApplicationId") long loanApplicationId) {
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			las.deleteLoanApplicationById(loanApplicationId);
			return "Deleted Successfully";
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@GetMapping("getAllLoanApplicationByLandStatus/{userId}")
	public List<LoanApplication> getLoanApplicationByLandStatus(@PathVariable("userId") int userId){
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("landverificationofficer")) {
			lvs.getLandOfficer(userId);
			return lvs.getLoanApplicationByStatus();
		}
		throw new EmptyInputException("211","This feature is only accessible to the Land verification officer");
	}
	
	@PutMapping("updateLoanApplicationLandOfficer/{userId}/{loanApplicationId}")
	public String updateLoanApplicationLand(@RequestParam boolean status,@PathVariable("userId") int userId,@PathVariable("loanApplicationId") int loanApplicationId) {
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("landverificationofficer")) {
			lvs.getLandOfficer(userId);
			LoanApplication loanApp=las.retrieveLoanApplicationById(loanApplicationId);
			if(loanApp.isFinanceVerificationApproval()&&loanApp.isLandVerificationApproval()) {
				throw new EmptyInputException("227","The Application is already passed to the Finance verification officer");
			}
			loanApp.setLandVerificationApproval(status);
			lvs.updateStatus(loanApp);
			return "Update succesfull";
		}
		throw new EmptyInputException("211","This feature is only accessible to the Land verification officer");
	}
	
	@GetMapping("getAllLoanApplicationByFinanceStatus/{userId}")
	public List<LoanApplication> getLoanApplicationByFinanceStatus(@PathVariable("userId") int userId){
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("financeverificationofficer")) {
			fvs.getFinance(userId);
			return fvs.getLoanApplicationByFinanceStatus();
		}
		throw new EmptyInputException("212","This feature is only accessible to the Finance verification officer");
	}
	
	@PutMapping("updateLoanApplicationFinanceOfficer/{userId}/{loanApplicationId}")
	public String updateLoanApplicationFinance(@RequestParam boolean status,@PathVariable("userId") int userId,@PathVariable("loanApplicationId") long loanApplicationId) {
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("financeverificationofficer")) {
			fvs.getFinance(userId);
			LoanApplication loanApp=las.retrieveLoanApplicationById(loanApplicationId);
			if(loanApp.isFinanceVerificationApproval()&&loanApp.isAdminApproval()) {
				throw new EmptyInputException("227","The Application is already passed to the Admin");
			}
			loanApp.setFinanceVerificationApproval(status);
			fvs.updateStatus(loanApp);
			return "Update successfull";
		}
		throw new EmptyInputException("212","This feature is only accessible to the Finance verification officer");
	}
	
	@PostMapping("addLoanApplicationCustomer/{userId}")
	public LoanApplication addLoanApplicationCustomer(@Valid @RequestBody LoanApplication loanApp,@PathVariable("userId") int userId) {
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("customer")) {
			if(loanApp.isAdminApproval()||loanApp.isFinanceVerificationApproval()||loanApp.isLandVerificationApproval()) {
				throw new EmptyInputException("207","This application can only be updated by the admin");
			}
			if(las.getLoanApplicationWithCustomerId(userId)!=null) {
				throw new EmptyInputException("223","The application for the customer is already exists");
			}
			if(csi.getCustomer(userId).getAadharNumber()==null) {
				throw new EmptyInputException("233","The customer data is invalid");
			}
			loanApp.setCustomerId(userId);
			loanApp.setStatus(Status.WAITING_FOR_LAND_VERIFICATION_OFFICE_APPROVAL);
			return las.addLoanApplication(loanApp);
		}
		throw new EmptyInputException("213","This feature is only accessible to the Customer");
	}
}
