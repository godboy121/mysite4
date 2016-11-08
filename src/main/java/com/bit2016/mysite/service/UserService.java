package com.bit2016.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.repository.UserDao;
import com.bit2016.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void list(UserVo vo){
		userDao.insert(vo);
		
	}
	public void join( UserVo vo ) {
		userDao.insert(vo);
	}
	
	
	public UserVo login(String email, String password){
		UserVo userVo=userDao.get(email, password);
		return userVo;
	}
	
	public UserVo getUser(Long no){
		UserVo uservo=userDao.get(no);
		return uservo;
	}
	
	public void modify(UserVo vo){
		userDao.update(vo);
	}
	
	public boolean emailExists(String email){
		return (userDao.get(email)!=null);
	}
	
	

}
