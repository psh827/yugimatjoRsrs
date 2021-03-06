package com.matjo.rsrs.restaurant;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matjo.rsrs.location.Location;

/**
 * Servlet implementation class SearchResServelt
 */
@WebServlet("/main/search_result.do")
public class SearchResServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantService restaurantService;
	private List<Restaurant> list;

	
	@Override
	public void init() throws ServletException {
		restaurantService = new RestaurantService(new RestaurantDao());
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		request.setCharacterEncoding("UTF-8");
		String resLocation1= request.getParameter("resLocation1");
		String resLocation2= request.getParameter("resLocation2");
		String resLocation = resLocation1 + " " + resLocation2;
		String resLocationDesc = resLocation2 + " " + resLocation1;
		String foodType = request.getParameter("foodType");
		String resCapacity = request.getParameter("resCapacity");
		
		resCapacity = resCapacity.split("인")[0];
		RequestDispatcher dispatcher = null;
		list = restaurantService.findResByCondition(resLocation, resLocationDesc, foodType, Integer.parseInt(resCapacity));
		if (list.size() == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('검색하신 결과가 없습니다.'); location.href='"+"/rsrs/main/main.jsp"+"';</script>"); 
			writer.close();
			return;
		}
		resLocation1 = redirect(resLocation1);
		resLocation2 = redirect(resLocation2);
		foodType = redirect(foodType);
		resCapacity = redirect(resCapacity);
		session.setAttribute("resLocation1", resLocation1);
		session.setAttribute("resLocation2", resLocation2);
		session.setAttribute("foodType", foodType);
		session.setAttribute("resCapacity", resCapacity);
		request.setAttribute("rList", list);
		dispatcher = request.getRequestDispatcher("/restaurant/result.jsp");
		dispatcher.forward(request, response);
		
	}

	public String subStringCost(String foodPrice) {
		String[] result = foodPrice.split(" ");
		String realResult = "";
		if(result.length <= 2) {
			realResult = result[0].split("원")[0];
			return realResult.replace(",", "");
		}
		realResult = result[2].split("원")[0];	
		
		realResult = realResult.replace(",", "");
		
		return realResult;
	}
	
	public String subStringFirstCost(String foodPrice) {
		String[] result = foodPrice.split(" ");
		String realResult = "";
		if(result.length <= 2) {
			realResult = result[0].split("원")[0];
			return realResult.replace(",", "");
		}
		realResult = result[0];	
		
		realResult = realResult.replace(",", "");
		
		return realResult;
	}
	public String redirect(String name) throws UnsupportedEncodingException {
	    String encodedParam = URLDecoder.decode(name, "UTF-8");

	    return encodedParam;
	}
}