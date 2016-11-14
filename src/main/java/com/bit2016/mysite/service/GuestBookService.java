package com.bit2016.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.repository.GuestbookDao;
import com.bit2016.mysite.vo.GuestbookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestbookVo> list() {

		return guestbookDao.getList();

	}

//	public List<GuestbookVo> list(int page) {
//
//		return guestbookDao.getList(page);
//
//	}

	public void delete(GuestbookVo vo) {
		guestbookDao.delete(vo);

	}

	public void add(GuestbookVo vo) {
		guestbookDao.insert(vo);
	}

}
