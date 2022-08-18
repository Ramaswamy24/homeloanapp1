package com.HomeLoanApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HomeLoanApp.Model.User;

public interface IUserRepository extends JpaRepository<User,Integer>{

}
