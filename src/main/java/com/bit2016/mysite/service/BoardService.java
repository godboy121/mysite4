package com.bit2016.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.repository.BoardDao;
import com.bit2016.mysite.vo.BoardVo;

@Service
public class BoardService {
	private static final int LIST_SIZE = 5; // �����õǴ� �Խù��� ��
	private static final int PAGE_SIZE = 5; // ������ ����Ʈ�� ������ ��

	@Autowired
	private BoardDao boardDao;

	public Map<String, Object> getList(int currentPage, String keyword) {

		// 1. ����¡�� ���� �⺻ ������ ���
		int totalCount = boardDao.getTotalCount(keyword);
		int pageCount = (int) Math.ceil((double) totalCount / LIST_SIZE);
		int blockCount = (int) Math.ceil((double) pageCount / PAGE_SIZE);
		int currentBlock = (int) Math.ceil((double) currentPage / PAGE_SIZE);

		// 2. �Ķ���� page �� ����
		if (currentPage < 1) {
			currentPage = 1;
			currentBlock = 1;
		} else if (currentPage > pageCount) {
			currentPage = pageCount;
			currentBlock = (int) Math.ceil((double) currentPage / PAGE_SIZE);
		}

		// 3. view���� ������ ����Ʈ�� ������ �ϱ����� ������ �� ���
		int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1) * PAGE_SIZE + 1;
		int prevPage = (currentBlock > 1) ? (currentBlock - 1) * PAGE_SIZE : 0;
		int nextPage = (currentBlock < blockCount) ? currentBlock * PAGE_SIZE + 1 : 0;
		int endPage = (nextPage > 0) ? (beginPage - 1) + LIST_SIZE : pageCount;

		// 4. ����Ʈ ��������
		List<BoardVo> list = boardDao.getList(keyword, currentPage, LIST_SIZE);

		// 5. ����Ʈ ������ �ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", list);
		map.put("totalCount", totalCount);
		map.put("listSize", LIST_SIZE);
		map.put("currentPage", currentPage);
		map.put("beginPage", beginPage);
		map.put("endPage", endPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("keyword", keyword);

		return map;
	}

}
