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
		//api 값을 담을 Map생성
		Map<String,String> resMap = new HashMap<String, String>();
		//api 호출
		resMap = GoogleApi.getLocation(resName);
		//위치값 저장
		String lng = getLng(resMap.get("location"));
		String lat = getLat(resMap.get("location"));
		
		//로드시 review 불러올 list
		List<Review> list = null;
		//상세페이지에 들어갈 레스토랑
		Restaurant s = null;
		//nickName을 찾기 위한 uId
		List<Long> uId = null;
		//nickName담기위한 리스트
		List<String> nickNameList = new ArrayList<String>();
		//nickName에 맞는 Grade를 담기위한 리스트
		List<String> nickNameGrade = new ArrayList<String>();
		//레스토랑 이름으로 레스토랑 정보 저장
		s = restaurantService.findResToSubpage(resName);
		//레스토랑 PK로 그 레스토랑 모든 리뷰 가져오기
		list = restaurantService.getAllReview(s.getRid());
		//레스토랑 PK로 리뷰테이블의 UserId가져오기
		uId = reviewService.getUidByResName(s.getRid());
		for(Long l : uId) {
			//유저 nick네임 저장
			nickNameList.add(userService.getNickNameByuId(l));
		}
		
		for(String grade : nickNameList) {
			//nickName별 등ㄱ브저장
			nickNameGrade.add(userService.findGradeByNickName(grade));
		}
		
		request.setAttribute("restaurant", s);
		request.setAttribute("reviewList", list);
		request.setAttribute("nickNameList", nickNameList);
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
