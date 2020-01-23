package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO()
	{
		String URL = "jdbc:mysql://localhost:3307/Board";
		String dbID = "root";
		String dbPassword = "1q2w3e4r!";
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL, dbID, dbPassword);
		} catch (Exception e){
			System.out.println("ERROR " + e);
		}		
	}
	
	public int login(String userID, String userPw)
	{
		String sql = "SELECT userPassword form user where userID = ?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString(1).equals(userPw)){
					return 1;
				}
				else {
					return 0;
				}
			}
			
		}catch (Exception e){
			System.out.println("ERROR " + e);
		}
		
		return -2;
	}
	
	public int join(UserDTO user)
	{
		String sql = "insert into user values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserEmail());
			return pstmt.executeUpdate();
		} catch (Exception e)
		{
			System.out.println("ERROR " + e);
		}
		return -1;
	}
	
}
