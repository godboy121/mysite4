package com.bit2016.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.mysite.repository.UserDao;
import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.UserVo;



@Controller("userAPIController")//usercontroller의 같은이름이 있을때 아이디값을 줘서 다르게 구별하게함
@RequestMapping("/user/api")//제이슨 응답하는곳 
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public Object checkEmail(
			@RequestParam(value="email",required=true,defaultValue="") String email){
		
		boolean result=userService.emailExists(email);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("result","success");
		if( result ) {
			map.put( "data", "not exist" );
		} else {
			map.put( "data", "exist" );
		}
		

		return map;
		
	}
}
