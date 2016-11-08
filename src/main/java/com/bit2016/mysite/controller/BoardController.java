package com.bit2016.mysite.controller;

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
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(@ModelAttribute BoardVo vo, Model model){
	
		
		model.addAttribute("vo",boardService.show(vo));
		
		return "board/view";
	}

	@RequestMapping( value="/write", method=RequestMethod.GET )
	public String write() {
		return "board/write";
	}
	
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