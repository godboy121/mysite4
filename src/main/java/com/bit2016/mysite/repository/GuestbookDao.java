package com.bit2016.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.mysite.vo.GuestbookVo;
@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;//sqlSession을 통해서 mybatis에 xml로 매핑된 쿼리문으로 데이터를 준다.
	

	

	
	public void delete(GuestbookVo vo) {
		sqlSession.delete( "guestbook.delete", vo );
		

	}
	
	public void insert(GuestbookVo vo ) {
		sqlSession.insert("guestbook.insert",vo);
	}
	
	public List<GuestbookVo> getList() {
		return sqlSession.selectList( "guestbook.getList" );
		
	}
}
//	
//	public List<GuestbookVo> getList( int page ) {
//		
//		
//		return sqlSession.selectOne("guestbook.getListByPage",page);
//		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = datasource.getConnection();
//			
//			String sql =
//				" select *" +
//				"  from (select a.*, rownum rn" +
//				"  from ( select no," +
//				" name, content, password, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss' )" +
//				" from guestbook" +
//				" order by reg_date desc ) a )" +
//				" where (?-1)*5+1 <= rn and rn <= ?*5";
//
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setInt(1, page);
//			pstmt.setInt(2, page);
//			rs = pstmt.executeQuery();
//			
//			while( rs.next() ) {
//				Long no = rs.getLong(1);
//				String name = rs.getString(2);
//				String content = rs.getString(3);
//				String password = rs.getString(4);
//				String regDate = rs.getString(5);
//				
//				GuestbookVo vo = new GuestbookVo();
//				vo.setNo(no);
//				vo.setName(name);
//				vo.setContent(content);
//				vo.setPassword(password);
//				vo.setRegDate(regDate);
//				
//				list.add( vo );
//			}
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if( rs != null ) {
//					rs.close();
//				}
//				if( pstmt != null ) {
//					pstmt.close();
//				}
//				if( conn != null ) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("error:" + e);
//			}
//		}
//
//		return list;
//	}	

