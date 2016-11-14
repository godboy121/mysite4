package com.bit2016.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bit2016.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter,
								  ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest,
								  WebDataBinderFactory binderFactory) throws Exception {
		if(supportsParameter(parameter)==false){
			return WebArgumentResolver.UNRESOLVED;
		}
		//@AuthUser 가 붙어있다는 뜻이다 여기까지 오면
		HttpServletRequest request=webRequest.getNativeRequest(HttpServletRequest.class);
		
		HttpSession session=request.getSession();
		if(session==null){
			return WebArgumentResolver.UNRESOLVED;
		}
	
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser=parameter.getParameterAnnotation(AuthUser.class);
		if(authUser==null){
			//@AuthUser이  붙어있지 x
			return false;
		}
		
		//parameter 타입이 uservo가 아니면
		if(parameter.getParameterType().equals(UserVo.class)==false){
			return false;
		}
		
		return true;
	}

}
