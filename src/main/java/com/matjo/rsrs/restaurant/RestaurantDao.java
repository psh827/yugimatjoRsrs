package com.matjo.rsrs.restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.matjo.rsrs.data.DataSource;
import com.matjo.rsrs.data.NamingService;

public class RestaurantDao {
	private DataSource dataSource;
	
	public RestaurantDao() {
		NamingService namingService = NamingService.getInstance();
		dataSource = (DataSource)namingService.getAttribute("dataSource");
	}
	public void addRes(Restaurant res) {
		String sql = "INSERT INTO Restaurant(resName, resLocation, resScore, "
				+ " foodType, foodPrice, resCapacity) "
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, res.getResName());
				pstmt.setString(2, res.getResLocation());
				pstmt.setDouble(3, res.getResScore());
				pstmt.setString(4, res.getFoodType());
				pstmt.setInt(5, res.getFoodPrice());
				pstmt.setInt(6, res.getResCapacity());
				pstmt.execute();
				System.out.println("INSERTED...");
			} finally {
				dataSource.close(pstmt, con);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public Restaurant findResByCondition(String)
}
