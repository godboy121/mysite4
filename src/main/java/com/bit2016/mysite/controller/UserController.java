package com.bit2016.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/loginform")
	public String loginForm() {
		return "user/loginform";

	}

	@RequestMapping("/joinform")
	public String joinForm() {

		return "user/joinform";
	}

	@RequestMapping("/join")
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result) {

		if (result.hasErrors()) {
			 List<ObjectError> list = result.getAllErrors();
			 for (ObjectError e : list) {
			 System.out.println("object Error:" + e);
			 }
			return "user/joinform";
		}
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";

	}

	@RequestMapping("/modifyform")
	public String modifyForm(HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		// 접근제한
		if (authUser == null) {
			return "redirect:/user/loginform";
		}
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);
		return "user/modifyform";
	}

	@RequestMapping("/modify")
	public String moify(HttpSession session, @ModelAttribute UserVo vo) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/user/loginform";
		}

		vo.setNo(authUser.getNo());
		userService.modify(vo);
		authUser.setName(vo.getName());
		return "redirect:/user/modifyform?update=success";
	}

}
