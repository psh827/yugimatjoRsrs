package com.matjo.rsrs.restaurant;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matjo.rsrs.googleapi.GoogleApi;
import com.matjo.rsrs.review.Review;

/**
 * Servlet implementation class ViewSubPage
 */
@WebServlet("/restaurant/subpage")
public class ViewSubPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RestaurantService restaurantService;

	@Override
	public void init() throws ServletException {
		restaurantService = new RestaurantService(new RestaurantDao());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resName = request.getParameter("resName");
		Map<String,String> resMap = new HashMap<String, String>();
		resMap = GoogleApi.getLocation(resName);
		String lng = getLng(resMap.get("location"));
		String lat = getLat(resMap.get("location"));
		
		List<Review> list = null;
		Restaurant s = null;
		
		s = restaurantService.findResToSubpage(resName);
		list = restaurantService.getAllReview(s.getRid());
		
		request.setAttribute("restaurant", s);
		request.setAttribute("reviewList", list);
		request.setAttribute("resName", resName);
		request.setAttribute("lng", lng);
		request.setAttribute("lat", lat);
			
		RequestDispatcher rd = request.getRequestDispatcher("/restaurant/subpage.jsp");
		rd.forward(request, response);
	}
	
	public String getLng(String location) {
		String[] lngLat = location.split(",");
		String lng = lngLat[0].replace("lng:", "");
		return lng;
	}
	
	public String getLat(String location) {
		String[] lngLat = location.split(",");
		String lat = lngLat[1].replace("lat:", "");
		return lat;
	}
}
