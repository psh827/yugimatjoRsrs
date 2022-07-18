package com.matjo.rsrs.restaurant;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matjo.rsrs.googleapi.GoogleApi;
import com.matjo.rsrs.location.Location;


/**
 * Servlet implementation class AddResServlet
 */
@WebServlet("/restaurant/add_res.do")
public class AddResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantService restaurantService;

	
	@Override
		public void init() throws ServletException {
			restaurantService = new RestaurantService(new RestaurantDao());
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		String userId = (String)session.getAttribute("userId");
		System.out.println(userId);
		if(userId == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('로그인해주세요.'); location.href='"+"/rsrs/login/login"+"';</script>"); 
			writer.close();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		Map<String,String> resMap = new HashMap<String, String>();
		String resName = request.getParameter("resName");
		resMap = GoogleApi.getDetail(resName);
		if(resMap.size() == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('해당식당은 없습니다. 다시 검색해주세요.'); location.href='"+"/rsrs/restaurant/add_res.jsp"+"';</script>"); 
			writer.close();
			request.getRequestDispatcher("add_res.jsp").forward(request, response);
			return;
		}
		
		if(restaurantService.isValidRes(resName)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('해당식당은 이미 존재합니다.'); location.href='"+"/rsrs/restaurant/add_res.jsp"+"';</script>"); 
			writer.close();
			request.getRequestDispatcher("add_res.jsp").forward(request, response);
			return;
		}
		
		String resLocation = resMap.get("addr");
		String resScore = resMap.get("rating");
		String foodType = request.getParameter("foodType");
		String foodPrice = request.getParameter("foodPrice");
		String resCapacity = request.getParameter("resCapacity");
		
		//2. 유효성 검증 및 변환
		
		RequestDispatcher dispatcher = null;
		
		Restaurant restaurant = new Restaurant();
		Location location = new Location(resLocation);
		restaurant.setResName(resName);
		restaurant.setResScore(Double.parseDouble(resScore));
		restaurant.setFoodType(foodType);
		restaurant.setFoodPrice(Integer.parseInt(foodPrice));
		
		//3. 비즈니스 서비스 호출
		restaurantService.addRes(restaurant, location);
		request.setAttribute("restaurant", restaurant);
		
		//4. NextPage
		request.setAttribute("resName", resName);
		dispatcher = request.getRequestDispatcher("/success/add_res_success.jsp");
		dispatcher.forward(request, response);
		
	}

}
