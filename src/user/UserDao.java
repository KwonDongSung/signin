package user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class UserDao {
	

    private PreparedStatement pstmt;  
    public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/patrickwon", "patrickwon",
					"patrick6!");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
    public int join(User user) {
        String SQL= "insert into signin(id,password,name,gender,email) values(?,?,?,?,?) ";
        try {
        	Connection con = getConnection();
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1,user.getUserId());
            pstmt.setString(2,user.getUserPassword());
            pstmt.setString(3,user.getUserName());
            pstmt.setString(4,user.getUserGender());
            pstmt.setString(5,user.getUserEmail());
            return pstmt.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

	public static List<User> getAllRecords() {
		List<User> list = new ArrayList<User>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from signin");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUserId(rs.getString("userId"));
				u.setUserPassword(rs.getString("userPassword"));
				u.setUserName(rs.getString("userName"));
				u.setUserGender(rs.getString("userGender"));
				u.setUserEmail(rs.getString("userEmail"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

}
