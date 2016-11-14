package com.bit2016.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2016.mysite.service.BoardService;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.security.Auth;

@Controller
@RequestMapping( "/board" )
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping( "" )
	public String index(
		@RequestParam( value="p", required=true, defaultValue="1") Integer page,
		@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
		Model model ) {
		
		Map<String, Object> map = 
				boardService.getList( page, keyword );
		
		model.addAttribute( "map", map );
		return "board/list";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete( 
			@RequestParam(value="no",required=true,defaultValue="") Long no,
			@RequestParam( value="p", required=true, defaultValue="1") Integer page,
			@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
			HttpSession session){
		Map <String, Object> map=new HashMap<String,Object>();
		map.put("no", no);
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );//유저번호를 가져오기 위해 세션 유지
		map.put("userNo", authUser.getNo());
		boardService.delete(map);//서비스에 보낸다 VO와함께
		
		

		
		
		
		return "redirect:/board?p="+page+"&kwd"+keyword;//
	}
	
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(@ModelAttribute BoardVo vo, Model model){
	
		
		model.addAttribute("vo",boardService.show(vo));
		
		return "board/view";
	}

	@RequestMapping( value="/write", method=RequestMethod.GET )
	public String write() {
		return "board/write";
	}
	@Auth(role="user")
	@RequestMapping( value="/write", method=RequestMethod.POST )
	public String write( HttpSession session, @ModelAttribute BoardVo vo ) {
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		// 권한 체크
		if( authUser == null ){
			return "redirect:/user/loginform";
		}
			
		vo.setUserNo( authUser.getNo() );
		boardService.write( vo );
		return "redirect:/board";
	}
	

	

	
}