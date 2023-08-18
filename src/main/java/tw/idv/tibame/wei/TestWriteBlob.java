package tw.idv.tibame.wei;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestWriteBlob {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/FurrEver?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "password";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			for(int i = 0;i < 11;i++){
				pstmt = con.prepareStatement("update product set p_pic_one=? where p_id=?");
				InputStream is = getPictureStream("C:/test/FurrEver/src/main/webapp/backEnd/productImages/p"+String.valueOf(i+1)+".jpg");
				pstmt.setBinaryStream(1, is);
				pstmt.setInt(2, i+1);
				pstmt.executeUpdate();	
				is.close();
			}
			
			for(int i = 0;i < 11;i++){
				pstmt = con.prepareStatement("update product set p_pic_two=? where p_id=?");
				InputStream is = getPictureStream("C:/test/FurrEver/src/main/webapp/backEnd/productImages/p"+String.valueOf(i+1)+"2.jpg");
				pstmt.setBinaryStream(1, is);
				pstmt.setInt(2, i+1);
				pstmt.executeUpdate();	
				is.close();
			}
			
			for(int i = 0;i < 11;i++){
				pstmt = con.prepareStatement("update product set p_pic_three=? where p_id=?");
				InputStream is = getPictureStream("C:/test/FurrEver/src/main/webapp/backEnd/productImages/p"+String.valueOf(i+1)+"3.jpg");
				pstmt.setBinaryStream(1, is);
				pstmt.setInt(2, i+1);
				pstmt.executeUpdate();	
				is.close();
			}
			
			for(int i = 0;i < 11;i++){
				pstmt = con.prepareStatement("update product set p_pic_four=? where p_id=?");
				InputStream is = getPictureStream("C:/test/FurrEver/src/main/webapp/backEnd/productImages/p"+String.valueOf(i+1)+"4.jpg");
				pstmt.setBinaryStream(1, is);
				pstmt.setInt(2, i+1);
				pstmt.executeUpdate();	
				is.close();
			}

			for(int i = 0;i < 6;i++){
				pstmt = con.prepareStatement("update user set u_pic=? where uid=?");
				InputStream is = getPictureStream("C:/test/FurrEver/src/main/webapp/backEnd/userImages/p"+String.valueOf(i+1)+".jpg");
				pstmt.setBinaryStream(1, is);
				pstmt.setInt(2, i+1);
				pstmt.executeUpdate();	
				is.close();
			}

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

	public static InputStream getPictureStream(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		return fis;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}

