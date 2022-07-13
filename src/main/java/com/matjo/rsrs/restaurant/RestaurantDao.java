package com.matjo.rsrs.restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.matjo.rsrs.ambiance.Ambiance;
import com.matjo.rsrs.data.DataSource;
import com.matjo.rsrs.data.NamingService;

public class RestaurantDao {
	private DataSource dataSource;
	
	public RestaurantDao() {
		NamingService namingService = NamingService.getInstance();
		dataSource = (DataSource)namingService.getAttribute("dataSource");
	}
	public void addRes(Restaurant res) {
		String sql = "INSERT INTO Restaurant(resName, resScore, "
				+ " foodType, foodPrice, resCapacity) "
				+ " VALUES (?, ?, ?, ?, ?)";
		
		try {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, res.getResName());
				pstmt.setDouble(2, res.getResScore());
				pstmt.setString(3, res.getFoodType());
				pstmt.setInt(4, res.getFoodPrice());
				pstmt.setInt(5, res.getResCapacity());
				pstmt.execute();
				System.out.println("INSERTED...");
			} finally {
				dataSource.close(pstmt, con);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Restaurant> findResByCondition(String resLocation, String foodType, int foodPrice, int resCapacity, String ambiance) {
		String sql = "Select r.*, ab.* FROM Restaurant r INNER JOIN Ambiance ab ON r.rId = ab.aId INNER JOIN Location lo ON r.rId = lo.resId WHERE lo.resLocation=? AND r.foodType=? "
				+ "AND r.foodPrice=? AND r.resCapacity=?";
		List<Restaurant> list = new ArrayList<>();
		try {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, resLocation);
				pstmt.setString(2, foodType);
				pstmt.setInt(3, foodPrice);
				pstmt.setInt(4, resCapacity);
				rs = pstmt.executeQuery();
				Restaurant res = null;
				while(rs.next()) {
					res.setResName(rs.getString("resName"));
					res.setResScore(rs.getDouble("resScore"));
					res.setFoodType(rs.getString("foodType"));
					res.setFoodPrice(rs.getInt("foodPrice"));
					res.setResCapacity(rs.getInt("resCapacity"));
					res.setAmbiance(new Ambiance());
					list.add(res);
				}
				
			} finally {
				dataSource.close(rs, pstmt, con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return list;
	}
}
