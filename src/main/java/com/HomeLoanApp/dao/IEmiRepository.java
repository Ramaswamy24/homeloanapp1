package com.HomeLoanApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HomeLoanApp.Model.Emi;

public interface IEmiRepository extends JpaRepository<Emi,Long>{

}
