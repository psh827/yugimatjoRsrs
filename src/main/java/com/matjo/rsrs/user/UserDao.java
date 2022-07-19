package com.matjo.rsrs.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.matjo.rsrs.data.DataSource;
import com.matjo.rsrs.data.NamingService;

public class UserDao {
	private DataSource dataSource;
	
	public UserDao() {
		NamingService namingService = NamingService.getInstance();
		dataSource =(DataSource) namingService.getAttribute("dataSource");
	}
	
	/**	 
	 * 회원가입 쿼리. FK로 묶여있는 Account(point)테이블과도 연결시킴.
	 * @param user
	 */
	public void addUser(User user) {	
		String sql = "INSERT INTO User(userName, userId, passwd, nickname)"
				+ "VALUE(?, ?, ?, ?)";
		String sql2 = "INSERT INTO Account(userId, point) values(?, 30)";
		
		try {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getUserName());
				pstmt.setString(2, user.getUserId());
				pstmt.setString(3, user.getPasswd());
				pstmt.setString(4, user.getNickname());
				pstmt.execute();
				System.out.println("USER INSERTED.....");
				pstmt = con.prepareStatement(sql2);
				User user2 = getUserId(user.getUserId());
				pstmt.setLong(1, user2.getuId());
				pstmt.execute();
				System.out.println("USER POINT INSERTED.....");
			}finally {
				dataSource.close(pstmt,con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * userId로 uId, nickname가져오는 쿼리.
	 * @param userId
	 * @return
	 */
	public User getUserId(String userId) {
	      String sql = "SELECT uId, nickName FROM User WHERE userId=?";
	      long rid = 0;
	      try {
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         try {
	            con = dataSource.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, userId);
	            rs = pstmt.executeQuery();
	            User user = null;
	            while(rs.next()) {
	               user = new User();
	               user.setuId(rs.getLong("uId"));
	               user.setNickname(rs.getString("nickName"));
	               return user;
	            }
	            
	         } finally {
	            dataSource.close(rs, pstmt, con);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	            
	      return null;
	   }
	
	/**
	 * userId, passwd로 유저인지 관리자인지 검사하는 쿼리.
	 * @param userId
	 * @param passwd
	 * @return
	 */
	public boolean isUserPosition(String userId, String passwd) {
	      String sql = "SELECT position FROM User WHERE userId = ? and passwd = ?";
	      boolean result = true;
	      try {
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         try {
	            con = dataSource.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, userId);
	            pstmt.setString(2, passwd);
	            rs = pstmt.executeQuery();
	            User user = null;
	            if(rs.next()) {
	            	user = new User();
	            	user.setPosition(rs.getString("position").charAt(0));
	            	if(user.getPosition() == 'M') {
	            		result =  false;
	            	}
	            }
	         } finally {
	            dataSource.close(rs, pstmt, con);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }      
	      return result;
	   }
	
	/**
	 * userId 중복 검사하는 쿼리.
	 * @param userId
	 * @return
	 */
	public boolean isValidUser(String userId) {
		  String sql = "SELECT * FROM User WHERE userId = ?";
	      boolean result = true;
	      try {
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         try {
	            con = dataSource.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, userId);
	            rs = pstmt.executeQuery();
	            if(!rs.next()) {
	            	result = false;
	            	return result;
	            }
	         } finally {
	            dataSource.close(rs, pstmt, con);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }      
	      return result;
	}
	
	/**
	 * userId, passwd로 유저가 이미 존재하는 검사하는 쿼리.
	 * @param userId
	 * @param passwd
	 * @return
	 */
	public boolean isValidUser(String userId, String passwd) {
		  String sql = "SELECT * FROM User WHERE userId = ? and passwd = ?";
	      boolean result = true;
	      try {
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         try {
	            con = dataSource.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, userId);
	            pstmt.setString(2, passwd);
	            rs = pstmt.executeQuery();
	            if(!rs.next()) {
	            	result = false;
	            	return result;
	            }
	         } finally {
	            dataSource.close(rs, pstmt, con);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }      
	      return result;
	}
	
	/**
	 * nickName 중복 검사를 위한 쿼리.
	 * @param nickname
	 * @return
	 */
	public boolean isValidUserByNickname(String nickname) {
		  String sql = "SELECT * FROM User WHERE nickName = ?";
	      boolean result = true;
	      try {
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         try {
	            con = dataSource.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, nickname);
	            rs = pstmt.executeQuery();
	            if(!rs.next()) {
	            	result = false;
	            	return result;
	            }
	         } finally {
	            dataSource.close(rs, pstmt, con);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }      
	      return result;
	}
	
	/**
	 * 유저가 가지고 있는 등급을 반환하는 쿼리.
	 * @param userId
	 * @return
	 */
	public String findGradeByUserId(String userId) {
	      String sql = "SELECT grade FROM User WHERE userId = ?";
	      String grade = "";
		try {
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         try {
	            con = dataSource.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, userId);
	            rs = pstmt.executeQuery();
	            User user = null;
	            while(rs.next()) {
	               user = new User();
	               user.setGrade(rs.getString("grade"));
	               grade = user.getGrade();
	            }
	            
	         } finally {
	            dataSource.close(rs, pstmt, con);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		
		return grade;
	   }

	/**
	 * userId로 그 유저가 가지고 있는 point를 반환해주는 쿼리.
	 * @param userId
	 * @return
	 */
	public int getPointByUserId(String userId) {
	      String sql = "SELECT a.point FROM Account a INNER JOIN User u ON a.userId = u.uId WHERE u.userId = ?";
	      int point = 0;
		try {
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         try {
	            con = dataSource.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, userId);
	            rs = pstmt.executeQuery();
	            while(rs.next()) {
	               point = rs.getInt("point");
	            }
	            
	         } finally {
	            dataSource.close(rs, pstmt, con);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		
		return point;
	   }
	
	/**
	 * uId로 nickName을 가져오는 쿼리
	 * @param l
	 * @return
	 */
	public String getNickNameByuId(Long l) {
		String sql = "SELECT nickName FROM User WHERE uId = ?";
		String nickName = "";
		try {
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         try {
	            con = dataSource.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setLong(1, l);
	            rs = pstmt.executeQuery();
	            while(rs.next()) {
	               nickName = rs.getString("nickName");
	            }
	            
	         } finally {
	            dataSource.close(rs, pstmt, con);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		
		return nickName;
	}
	
	public String findGradeByNickName(String nickName) {
	      String sql = "SELECT grade FROM User WHERE nickName = ?";
	      String grade = "";
		try {
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         try {
	            con = dataSource.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, nickName);
	            rs = pstmt.executeQuery();
	            while(rs.next()) {
	               grade = rs.getString("grade");
	            }
	            
	         } finally {
	            dataSource.close(rs, pstmt, con);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		
		return grade;
	   }

}
