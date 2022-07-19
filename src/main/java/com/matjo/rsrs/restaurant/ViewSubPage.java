package com.matjo.rsrs.restaurant;

import java.io.IOException;
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
import com.matjo.rsrs.review.Review;
import com.matjo.rsrs.review.ReviewDao;
import com.matjo.rsrs.review.ReviewService;
import com.matjo.rsrs.user.UserDao;
import com.matjo.rsrs.user.UserService;

/**
 * Servlet implementation class ViewSubPage
 */
@WebServlet("/restaurant/subpage")
public class ViewSubPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RestaurantService restaurantService;
	private ReviewService reviewService;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		restaurantService = new RestaurantService(new RestaurantDao());
		reviewService = new ReviewService(new ReviewDao());
		userService = new UserService(new UserDao());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resName = request.getParameter("resName");
		Map<String,String> resMap = new HashMap<String, String>();
		resMap = GoogleApi.getLocation(resName);
		String lng = getLng(resMap.get("location"));
		String lat = getLat(resMap.get("location"));
		
		List<Review> list = null;
		Restaurant s = null;
		List<Long> uId = null;
		List<String> nickNameList = new ArrayList<String>();
		List<String> nickNameGrade = new ArrayList<String>();
		s = restaurantService.findResToSubpage(resName);
		list = restaurantService.getAllReview(s.getRid());
		uId = reviewService.getUidByResName(s.getRid());
		for(Long l : uId) {
			nickNameList.add(userService.getNickNameByuId(l));
		}
		
		for(String grade : nickNameList) {
			nickNameGrade.add(userService.findGradeByNickName(grade));
		}
		
		request.setAttribute("restaurant", s);
		request.setAttribute("reviewList", list);
		request.setAttribute("nickName", nickNameList);
		request.setAttribute("resName", resName);
		request.setAttribute("nickNameGrade", nickNameGrade);
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
