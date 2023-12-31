package tw.idv.tibame.payStatus.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PayStatusDAO implements PayStatusDAO_interface {
	@Autowired
	private DataSource ds;

	private static final String GET_ALL_STMT = "SELECT pa_id , pa_name FROM paystatus";

	@Override
	public List<PayStatusVO> getAll() {
		List<PayStatusVO> list = new ArrayList<PayStatusVO>();
		PayStatusVO payStatusVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				payStatusVO = new PayStatusVO();
				payStatusVO.setPa_id(rs.getInt("pa_id"));
				payStatusVO.setPa_name(rs.getString("pa_name"));
				list.add(payStatusVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
