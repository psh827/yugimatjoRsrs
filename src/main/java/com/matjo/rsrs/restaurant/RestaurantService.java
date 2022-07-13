package com.matjo.rsrs.restaurant;

public class RestaurantService {
	private RestaurantDao restaurantDao;
	
	public RestaurantService(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}
	
	public void addRes(Restaurant restaurant) {
		restaurantDao.addRes(restaurant);
		
	}

}
