package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BbsDAO {
	private Connection conn;
	//private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BbsDAO()
	{
		String URL = "jdbc:mysql://localhost:3307/Board?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String dbID = "root";
		String dbPassword = "1q2w3e4r!";
		
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(URL, dbID, dbPassword);
		} catch (Exception e){
			System.out.println("ERROR " + e);
		}		
	}
	
	public ArrayList<Bbs> getList(int pageNum){
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		
		ArrayList<Bbs> array = new ArrayList<Bbs>();
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNum() - (pageNum-1)*10);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvaliable(rs.getInt(6));
				
				array.add(bbs);
				System.out.println("list add  : "+bbs.getBbsID());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return array;
	}
	public boolean nextPage(int pageNum){
		
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		ArrayList<Bbs> array = new ArrayList<Bbs>();
		try{
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNum() - (pageNum-1)*10);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public int getNum() { 

		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	public String getDate() { 

		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; //데이터베이스 오류
	}

	public int write(String bbsTitle, String userID, String bbsContent){
		
		String SQL = "Insert INTO BBS VALUES(?, ?, ?, ?, ?, ?)";
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyy-MM-dd hh:mm:s").format(date));
        System.out.println(getNum());
        System.out.println(today);
	
		try{
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNum());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, today);
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);
			
			return pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
			return -1;
		}	
	}
	
	public Bbs getBbs(int bbsID){
		String SQL = "SELECT * FROM bbs where bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvaliable(rs.getInt(6));
				
				return bbs;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
//	public int write(String bbsTitle, String userID, String bbsContent) { 
//
//		String SQL = "INSERT INTO BBS VALUES(?, ?, ?, ?, ?, ?)";
//
//		try {
//
//			
//
//			PreparedStatement pstmt = conn.prepareStatement(SQL);
//
//			pstmt.setInt(1, getNum());
//			pstmt.setString(2, bbsTitle);
//			pstmt.setString(3, userID);
//			pstmt.setString(4, getDate());
//			pstmt.setString(5, bbsContent);
//			pstmt.setInt(6,1);
//
//			return pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return -1; //데이터베이스 오류
//
//	}
//
	
	public int update(int bbsID, String bbsTitle, String bbsContent){
		String SQL = "UPDATE BBS SET bbsTitle =?, bbsContent=?, bbsDate=? where bbsID=?";
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyy-MM-dd hh:mm:s").format(date));
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);		
			pstmt.setString(3, today);
			pstmt.setInt(4, bbsID);
			return pstmt.executeUpdate();
						
		} catch (Exception e){
			e.printStackTrace();
		}
		return -1;
	}
	
	public int delete(int bbsID){
		String SQL = "DELETE FROM BBS where bbsID = ?";
		try{
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			
			return pstmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}
		return -1;
	}
}

