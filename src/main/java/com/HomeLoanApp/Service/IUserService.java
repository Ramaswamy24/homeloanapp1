package com.HomeLoanApp.Service;

import com.HomeLoanApp.Model.User;

public interface IUserService {
	public User addNewUser(User user);
	public User signIn(User user);
	public User signOut(User user);
	public User findUserWithId(int userId);
	public void deleteUser(int userId);
}
