package com.matjo.rsrs.review;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ReviewDao {
	
	public void addReview(Review review) {
		String sql = "INSERT INTO Review(userId, resId, reviewText, recommandScore)" + "VALUES(?,?,?,?)";
		try {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DataSourceManager.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, review.getUserId());  //session으로 유저아이디 연결해보자 
				pstmt.setLong(2, review.getResId());
				pstmt.setString(3, review.getReviewText());
				pstmt.setDouble(4, review.getRecommandScore());
				pstmt.executeUpdate();
			} finally  {
				DataSourceManager.close(pstmt,con);	
			}	
			System.out.println("NEW Review INSERTED.....\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateAccount(Review review) {
		String sql = "UPDATE Review SET reviewText=?, regDate=? WHERE userId=?";
		try {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DataSourceManager.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, review.getReviewText());
				pstmt.setDate(2, (Date)review.getRegDate());
				pstmt.executeUpdate();
			} finally  {
				DataSourceManager.close(pstmt,con);	
			}	
			System.out.println("NEW Review UPDATE.....\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public String findUserByUserId(String userId) {
		Stirng sql = "SELECT * FROM User3 WHERE userId = ?";
		
		
		return userId;
	}

}
