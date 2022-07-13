package com.matjo.rsrs.restaurant;

import java.util.List;

public class RestaurantService {
	private RestaurantDao restaurantDao;
	
	public RestaurantService(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}
	
	public void addRes(Restaurant restaurant) {
		restaurantDao.addRes(restaurant);
		
	}

	public List<Restaurant> findResByCondition(String resLocation, String foodType, int foodPrice, int resCapacity, String ambiance) {
		return restaurantDao.findResByCondition(resLocation, foodType, foodPrice, resCapacity, ambiance);
		
	}

}
