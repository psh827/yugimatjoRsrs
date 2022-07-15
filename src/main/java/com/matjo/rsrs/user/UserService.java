package com.matjo.rsrs.user;

public class UserService {
	private UserDao userDao;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	public void addUser(User user) {
		userDao.addUser(user);
	}
	
	public boolean isValidUser(String userId, String passwd) {
		return userDao.isValidUser(userId, passwd);
	}
	
	public boolean isUserPosition(String userId, String passwd) {
		return userDao.isUserPosition(userId, passwd);
	}
	
}
