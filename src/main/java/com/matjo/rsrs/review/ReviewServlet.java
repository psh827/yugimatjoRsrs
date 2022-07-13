package com.matjo.rsrs.review;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/review/ResReview.do") //로그인한후
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ReviewService reviewService;
	
	public void init() {
		reviewService = new ReviewService(new ReviewDao());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 폼 파라메터 열기
		String userId = request.getParameter("userId");
		String resId = request.getParameter("resId");
		String reviewText = request.getParameter("reviewText");
		String recommandScore = request.getParameter("recommandScore");

		// 2. 유효성 검증 및 변환
		List<String> errorMsgs = new ArrayList<>();
		if(reviewText == null) {
			errorMsgs.add("리뷰내용을 입력해주세요.");
		}
		
		Review review = new Review();
		review.setUserId(Long.parseLong(userId));
		review.setResId(Long.parseLong(resId));
		review.setReviewText(reviewText);
		review.setRecommandScore(Integer.parseInt(recommandScore));

		//3. 비즈니스 서비스 호출
		reviewService.addReview(review);
		request.setAttribute("review", review);
		

	}
}
