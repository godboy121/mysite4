package com.bit2016.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit2016.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response,
							 Object handler /*handler Method*/)
			throws Exception {
		
		//1.handler 종류
		if(handler instanceof HandlerMethod == false){
			return true;
		}
		
		//2.@Auth가 붙어있는지 여부 
		Auth auth=((HandlerMethod)handler).getMethodAnnotation(Auth.class);//캐스팅
		if(auth==null){//접근제어가 필요없는 handler
			return true;
			
		}
		//3.접근 제어
		HttpSession session=request.getSession();
		if(session==null){
			response.sendRedirect(request.getContextPath()+"/user/loginform");
			return false;
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			response.sendRedirect(request.getContextPath()+"/user/loginform");
			return false;
		}
		
	
		return true;
	}

}
