package com.HomeLoanApp.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApp.Classes.EMICalculator;
import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.Emi;
import com.HomeLoanApp.Model.User;
import com.HomeLoanApp.Service.AdminServiceImpl;
import com.HomeLoanApp.Service.EmiServiceImpl;
import com.HomeLoanApp.Service.UserServiceImpl;

@RestController
@RequestMapping("emi")
public class EMICalculationController {
	
	@Autowired
	private EmiServiceImpl esi;
	
	@Autowired
	private UserServiceImpl usi;
	
	@Autowired
	private AdminServiceImpl asi;
	
	@GetMapping("calculateEmi")
	public ResponseEntity<String> calculateEmi(@Valid @RequestBody EMICalculator emi){
		return new ResponseEntity<String>(String.format("EMI amount is : %.4f",esi.calculateEmi(emi)),HttpStatus.OK);
	}
	
	@PostMapping("addEmi/{userId}/{agreementId}")
	public Emi addEmi(@Valid @RequestBody EMICalculator emi,@PathVariable("userId") int userId,@PathVariable("agreementId") long agreementId){
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			if(agreementId!=0) {
				return esi.addEmi(emi, agreementId);
			}
			throw new EmptyInputException("200","Wrong Input");
		}
		throw new EmptyInputException("215","Emi can only be accessed by the admin");
	}
	
	@GetMapping("getEmi/{userId}/{agreementId}")
	public Emi getEmi(@PathVariable("userId") int userId,@PathVariable("agreementId") long agreementId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			if(agreementId!=0) {
				return esi.getEmi(agreementId);
			}
			throw new EmptyInputException("200","wrong input");
		}
		throw new EmptyInputException("215","Emi can only be accessed by the admin");
	}
	
	@GetMapping("getAllEmi/{userId}")
	public List<Emi> getAllEmi(@PathVariable("userId") int userId){
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			return esi.getAllEmi();
		}
		throw new EmptyInputException("215","Emi can only be accessed by the admin");
	}
	
	@DeleteMapping("deleteEmi/{userId}/{emiId}")
	public String deleteEmi(@PathVariable("userId") int userId,@PathVariable("emiId") long emiId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			asi.getAdmin(userId);
			esi.deleteEmiById(emiId);
			return "Delete Successfull";
		}
		throw new EmptyInputException("215","Emi can only be accessed by the admin");
	}
}
