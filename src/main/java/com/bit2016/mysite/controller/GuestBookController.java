package com.bit2016.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit2016.mysite.repository.GuestbookDao;
import com.bit2016.mysite.service.GuestBookService;
import com.bit2016.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestbookService;
	
	
	@RequestMapping("")
	public String listForm(Model model){//jsp에 data를 넣어줄때는 model을 사용해서 넘겨주어야한다.
		List<GuestbookVo> list=	guestbookService.list();
		model.addAttribute("list",list);
		
		return "guestbook/list";
	}
	
	@RequestMapping("/deleteform/{no}")
	public String deleteform(@PathVariable("no") Long no,Model model){
		model.addAttribute("no",no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping( value="/delete", method=RequestMethod.POST )
	public String delete( @ModelAttribute GuestbookVo vo ){
		guestbookService.delete(vo);
	
		return "redirect:/guestbook";
	}
	
	@RequestMapping( value="/add", method=RequestMethod.POST )
	public String add( @ModelAttribute GuestbookVo vo ) {
		guestbookService.add(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping("/ajax")
	public String ajax(){
		return "guestbook/list-ajax";
	}
	
	
	
	
}
