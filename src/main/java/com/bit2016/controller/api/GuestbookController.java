//package com.bit2016.controller.api;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.bit2016.mysite.service.GuestBookService;
//import com.bit2016.mysite.vo.GuestbookVo;
//
//@Controller("guestbookAPIController")
//@RequestMapping("/guestbook/api")
//public class GuestbookController {
//	
//	
//	@Autowired
//	private GuestBookService guestbookService; 
//	
//		@ResponseBody
//		@RequestMapping("/list")
//		public Object list(@RequestParam(value="p",required=true,defaultValue="1") Integer page ){
//			List<GuestbookVo> list=guestbookService.list(page);
//			
//			Map<String,Object> map=new HashMap<String,Object>();
//			map.put("result","success");
//			map.put("data",list);
//			return map;
//		}
//}
