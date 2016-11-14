package com.bit2016.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response,
							 Object handler/*HandlerMethod */)
							 throws Exception {
		System.out.println("AuthLoginInterceptor.preHandle called");
		
		
		//로그인
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		//userservice의 login하려면 콘테이너를 불러야한다, WebApplication context 받아오기 ->userservice 객체를 받아오기위해
		ApplicationContext ac=
				WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		//Container 안에있는 userservice bean(객체) 받아오기
		UserService userService=ac.getBean(UserService.class);//userservice의 클래스이름을 타입으로
		
		//DB에서 해당 USERVo 받아오기
		UserVo userVo=userService.login(email, password);
		
		//email과 password가 일치하지 않는 경우
		if(userVo==null){
			response.sendRedirect(request.getContextPath()+"/user/loginform?result=fail");
			return false;
		}
		
		//인증처리
		HttpSession session=request.getSession(true);
		session.setAttribute("authUser", userVo);
		response.sendRedirect(request.getContextPath());
		
		return false;
	}

}
