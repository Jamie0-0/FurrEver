package tw.idv.tibame.pMapping.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PMappingDAO implements PMappingDAO_interface {
	@Autowired
	private DataSource ds;

	private static final String INSERT_STMT = "INSERT INTO prodMapping (pm_name) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT pm_id , pm_name FROM prodMapping";
	private static final String GET_ONE_STMT = "SELECT pm_id , pm_name FROM prodMapping where pm_id = ?";

	@Override
	public void insert(PMappingVO pMappingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, pMappingVO.getPm_name());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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

	}

	@Override
	public PMappingVO findByPrimaryKey(Integer pm_id) {

		PMappingVO pMappingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pMappingVO = new PMappingVO();
				pMappingVO.setPm_id(rs.getInt("pm_id"));
				pMappingVO.setPm_name(rs.getString("pm_name"));

			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return pMappingVO;
	}

	@Override
	public List<PMappingVO> getAll() {
		List<PMappingVO> list = new ArrayList<PMappingVO>();
		PMappingVO pMappingVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pMappingVO = new PMappingVO();
				pMappingVO.setPm_id(rs.getInt("pm_id"));
				pMappingVO.setPm_name(rs.getString("pm_name"));
				list.add(pMappingVO);
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
