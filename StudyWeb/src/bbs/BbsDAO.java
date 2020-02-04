package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BbsDAO {
	private Connection conn;
	private PreparedStatement pstmt;
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
	
	public int getNum(){
		String SQL = "select count(*) from BBS";
		try{
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			return rs.getInt(1)+1;
		} catch (Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	public int write(String bbsTitle, String userID, String bbsContent){
		
		String SQL = "Insert into BBS values(?,?,?,?,?,?)";
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));

	
		try{
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNum());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, today);
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);
		} catch(Exception e){
			e.printStackTrace();
		}
		
	
		return -1;
	}
}
