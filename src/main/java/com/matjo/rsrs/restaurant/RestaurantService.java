package com.matjo.rsrs.restaurant;

import java.util.List;

import com.matjo.rsrs.location.Location;

public class RestaurantService {
	private RestaurantDao restaurantDao;
	
	public RestaurantService(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}
	
	public void addRes(Restaurant restaurant, Location location) {
		restaurantDao.addRes(restaurant, location);	
	}
	
	public List<Restaurant> findResByCondition(String resLocation, String foodType, int foodPrice, int resCapacity) {
		return restaurantDao.findResByCondition(resLocation, foodType, foodPrice, resCapacity);
	}

}
