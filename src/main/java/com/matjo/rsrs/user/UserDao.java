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
	
	public void addUser(User user) {	
		String sql = "INSERT INTO User(userName, userId, passwd, nickname)"
				+ "VALUE(?, ?, ?, ?)";
		
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
				System.out.println("INSERTED.....");
			}finally {
				dataSource.close(pstmt,con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
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
	            System.out.println(rs);
	            if(!rs.next()) {
	               result = false;
	            }
	         } finally {
	            dataSource.close(rs, pstmt, con);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }      
	      return result;
	   }

	
		


}
