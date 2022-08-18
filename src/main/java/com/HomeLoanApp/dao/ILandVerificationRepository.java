package com.HomeLoanApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HomeLoanApp.Model.LandVerificationOfficer;

public interface ILandVerificationRepository extends JpaRepository<LandVerificationOfficer,String>{

}
