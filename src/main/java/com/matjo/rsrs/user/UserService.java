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
	
	public boolean isValidUser(String userId) {
		return userDao.isValidUser(userId);
	}
	
	public boolean isUserPosition(String userId, String passwd) {
		return userDao.isUserPosition(userId, passwd);
	}
	
	public User getUserId(String userId) {
		return userDao.getUserId(userId);
	}
	public String getGrade(String grade){
	    return userDao.findGradeByUserId(grade);
	}
	
	public boolean isValidUserByNickname(String nickname) {
		return userDao.isValidUserByNickname(nickname);
	}
	public String getNickNameByuId(Long l) {
		return userDao.getNickNameByuId(l);
	}
	public int getPointByUserId(String userId) {
		return userDao.getPointByUserId(userId);
	}
}
