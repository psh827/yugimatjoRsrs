package com.matjo.rsrs.restaurant;

import java.io.IOException;
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
@WebServlet("/restaurant/search_result.do")
public class SearchResServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantService restaurantService;

	
	@Override
	public void init() throws ServletException {
		restaurantService = new RestaurantService(new RestaurantDao());
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-Kr");
		String resLocation= request.getParameter("resLocation");
		String foodType = request.getParameter("foodType");
		String foodPrice = request.getParameter("foodPrice");
		String resCapacity = request.getParameter("resCapacity");
		
		RequestDispatcher dispatcher = null;
		List<Restaurant> list = restaurantService.findResByCondition(resLocation, foodType, Integer.parseInt(foodPrice), Integer.parseInt(resCapacity));
		
		request.setAttribute("list", list);
		dispatcher = request.getRequestDispatcher("/restaruant/search_result.jsp");
		dispatcher.forward(request, response);
		
	}

}
