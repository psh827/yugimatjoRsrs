package com.matjo.rsrs.restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matjo.rsrs.location.Location;

/**
 * Servlet implementation class SearchResServelt
 */
@WebServlet("/restaurant/search_res")
public class SearchResServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RestaurantService restaurantService;
	
	@Override
	public void init() throws ServletException {
		restaurantService = new RestaurantService(new RestaurantDao());
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-Kr");
		String resLocation = request.getParameter("resLocation");
		String foodType = request.getParameter("foodType");
		String foodPrice = request.getParameter("foodPrice");
		String resCapacity = request.getParameter("resCapacity");
		
		RequestDispatcher dispatcher = null;
		Restaurant restaurant = new Restaurant();
		restaurant.setLocation(new Location(resLocation));
		restaurant.setFoodType(foodType);
		restaurant.setFoodPrice(Integer.parseInt(foodPrice));
		restaurant.setResCapacity(Integer.parseInt(resCapacity));
		
		//3. 비즈니스 서비스 호출
		restaurantService.findResByCondition(restaurant);
		request.setAttribute("restaurant", restaurant);
		
		//4. NextPage
		request.setAttribute("resName", resName);
		dispatcher = request.getRequestDispatcher("/success/add_res_success.jsp");
		dispatcher.forward(request, response);
		
	}


}
