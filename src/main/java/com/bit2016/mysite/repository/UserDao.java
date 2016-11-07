package com.bit2016.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.mysite.exception.UserDaoException;
import com.bit2016.mysite.vo.UserVo;
@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource datasource;
	
	
	//private Connection getConnection() throws SQLException {
	//	Connection conn = null;
		//try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//String url = "jdbc:oracle:thin:@localhost:1521:xe";
			//conn = DriverManager.getConnection(url, "webdb", "webdb");
		//} catch (ClassNotFoundException e) {
			//System.out.println("드라이버 로딩 실패 :" + e);
		//}
		//return conn;
	//}
	
	// 인증(로그인)
	public UserVo get(String email, String password) throws UserDaoException {
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("email",email);
		map.put("password",password);
		UserVo vo=sqlSession.selectOne("user.getByEmailAndPassword",map);
//		UserVo vo = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = datasource.getConnection();
//			
//			String sql = 
//				" select no, name" + 
//				"   from users" +
//				"  where email=?" +
//				"    and password=?";
//			pstmt = conn.prepareStatement( sql );
//
//			pstmt.setString(1, email);
//			pstmt.setString(2, password);
//			
//			rs = pstmt.executeQuery();
//			if( rs.next() ) {
//				Long no = rs.getLong( 1 );
//				String name = rs.getString( 2 );
//				
//				vo = new UserVo();
//				vo.setNo(no);
//				vo.setName(name);
//			}
//		} catch (SQLException e) {
//			System.out.println( "error:" + e );
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
//			} catch( SQLException e ) {
//				System.out.println( "error:" + e );
//			}
//		}
//		
	return sqlSession.selectOne("user.getByEmailAndPassword",map);
	}
	
	// 이메일 체크
	public UserVo get( String email ) {
		return sqlSession.selectOne("user.getByEmail",email);
//		UserVo vo = null;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = datasource.getConnection();
//			
//			String sql =
//				" select no, email, name" +
//				"   from users" +
//				"  where email=?";
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, email);
//			rs = pstmt.executeQuery();
//			
//			if( rs.next() ) {
//				vo = new UserVo();
//				vo.setNo( rs.getLong(1) );
//				vo.setEmail( rs.getString( 2 ) );
//				vo.setName( rs.getString( 3 ) );
//			}
//			
//		} catch( SQLException ex  ){
//			System.out.println( "error:" + ex );
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
//			} catch( SQLException ex ) {
//				System.out.println( "error:" + ex);
//			}
//		}
//		
//		return vo;
	}
	
	// 사용자 가져오기
	public UserVo get(Long no) {
		return sqlSession.selectOne("user.getByNo",no);
//		UserVo vo = null;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = datasource.getConnection();
//			
//			String sql = 
//				" select no, name, email, gender" + 
//				"   from users" +
//				"  where no=?";
//			pstmt = conn.prepareStatement( sql );
//
//			pstmt.setLong(1, no);
//
//			rs = pstmt.executeQuery();
//			if( rs.next() ) {
//				vo = new UserVo();
//				vo.setNo( rs.getLong( 1 ) );
//				vo.setName( rs.getString( 2 ) );
//				vo.setEmail( rs.getString( 3 ) );
//				vo.setGender( rs.getString( 4 ) );
//			}
//		} catch (SQLException e) {
//			System.out.println( "error:" + e );
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
//			} catch( SQLException e ) {
//				System.out.println( "error:" + e );
//			}
//		}
//		
//		return vo;
	}
	
	public void insert( UserVo vo ) {
		sqlSession.insert("user.insert",vo);
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = datasource.getConnection();
//			
//			String sql =
//				" insert" +
//				"   into users" +
//				" values( user_seq.nextval, ?, ?, ?, ?)";
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getEmail());
//			pstmt.setString(3, vo.getPassword());
//			pstmt.setString(4, vo.getGender() );
//			
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println( "error:" + e );
//		} finally {
//			try {
//				if( pstmt != null ) {
//					pstmt.close();
//				}				
//				if( conn != null ) {
//					conn.close();
//				}
//			}catch( SQLException e ) {
//				System.out.println( "error:" + e);
//			}
//		}
	}
	

	
	public void update( UserVo vo ) {
	//	sqlSession.update("user.update",vo);
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			
			String sql = "update users set name = ?, gender = ?";
			if( "".equals( vo.getPassword() ) == false ){
				sql += ", password=?";
			}
			sql += " where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender() );
			if( "".equals( vo.getPassword() ) == false ){
				pstmt.setString( 3, vo.getPassword() );
				pstmt.setLong( 4, vo.getNo() );
			} else {
				pstmt.setLong( 3, vo.getNo() );
			}
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println( "error:" + e );
		} finally {
			try {
				if( pstmt != null ) {
					pstmt.close();
				}				
				if( conn != null ) {
					conn.close();
				}
			}catch( SQLException e ) {
				System.out.println( "error:" + e);
			}
		}
	}	
}
