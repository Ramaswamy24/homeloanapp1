package com.HomeLoanApp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.User;
import com.HomeLoanApp.dao.IUserRepository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository ur;
	@Override
	public User addNewUser(User user) {
		
		if(user.getPassword().isEmpty()||user.getRole().isEmpty()) {
			throw new EmptyInputException("200","Wrong input");
		}
		
		return ur.save(user);
	}

	@Override
	public User signIn(User user) {
		List<User> l1=ur.findAll();
		
		for(User u1:l1) {
			if(u1.getUserId()==user.getUserId()&&u1.getPassword().equals(user.getPassword())) {
				return user;
			}
		}
		throw new EmptyInputException("201","Wrong Password/UserId");
	}

	@Override
	public User signOut(User user) {
		if(user==null)
			throw new EmptyInputException("202","User doesn't exist");
		return user;
	}

	
	@Override
	public User findUserWithId(int userId) {
		List<User> l1=ur.findAll();
		for(User u1:l1) {
			if(u1.getUserId()==userId) {
				return u1;
			}
		}
		
		throw new EmptyInputException("202","user Id doesn't exist");
	}

	@Override
	public void deleteUser(int userId) {
		User u=ur.findAll().stream().filter(user->user.getUserId()==userId).collect(Collectors.toList()).get(0);
		if(u!=null) {
			ur.delete(u);
			return;
		}
		throw new EmptyInputException("202","user Id doesn't exist");
	}
	
}
