package com.bit2016.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception { //해당 컨트롤러의 메소드를 호출하기전에 호출되는 intercepter함수
		System.out.println("my interceptor.preHandle called");
		return true;
	}

	
	
	
	//잘 안쓰니까 default로 구현해놓는다.
	@Override//응답나가기전에
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("my interceptor.afterCompletion called");

	}

	@Override//해당 컨트롤러의 메소드 호출다음에 호출되는 함수 
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("my interceptor.postHandle called");

	}
}
