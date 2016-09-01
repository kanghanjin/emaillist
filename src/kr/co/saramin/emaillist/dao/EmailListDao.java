package kr.co.saramin.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.saramin.emaillist.vo.EmailListVo;

public class EmailListDao {
	public void insert( EmailListVo vo) {
		List<EmailListVo> list = new ArrayList<EmailListVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			stmt = conn.createStatement();
			
			String sql = "insert into emaillist values (null, ?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver Loding Fail");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error : " + e);
		} finally {
			try{
				if (pstmt != null) {
					pstmt.close();					
				}
				
				if (conn != null) {
					conn.close();					
				}	
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	public List<EmailListVo> getList() {
		List<EmailListVo> list = new ArrayList<EmailListVo>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			stmt = conn.createStatement();
			
			String sql = "select * from emaillist";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long no 		 = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName  = rs.getString(3);
				String email     = rs.getString(4);
				
				EmailListVo vo = new EmailListVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
				list.add(vo);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver Loding Fail");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error : " + e);
		} finally {
			try{
				if (rs != null) {
					rs.close();					
				}
				
				if (stmt != null) {
					stmt.close();					
				}
				
				if (conn != null) {
					conn.close();					
				}	
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return list;
	}
}
