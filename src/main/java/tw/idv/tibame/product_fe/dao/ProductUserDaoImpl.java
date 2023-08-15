package tw.idv.tibame.product_fe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.product_fe.vo.ProductUser;

@Repository
public class ProductUserDaoImpl implements ProductUserDao {

	@Autowired
	private DataSource ds;

	@Override
	public ProductUser selectByUserName(String name) {
		final String sql = "select * from USER where u_name = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql);) {
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				ProductUser productUser = new ProductUser();
				productUser.setUid(rs.getInt("uid"));
				productUser.setU_name(rs.getString("u_name"));
				return productUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductUser selectByUserNameForCart(String name) {
		final String sql = "select * from USER where u_name = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql);) {
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				ProductUser productUser = new ProductUser();
				productUser.setUid(rs.getInt("uid"));
				productUser.setU_name(rs.getString("u_name"));
				productUser.setU_phone(rs.getString("u_phone"));
				productUser.setU_address(rs.getString("u_address"));
				return productUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductUser selectByUidForCart(int uid) {
		final String sql = "select uid, u_name, u_phone, u_address from USER where uid = ?;";
		try (Connection connection = ds.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql);) {
			pstm.setInt(1, uid);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				ProductUser productUser = new ProductUser();
				productUser.setUid(rs.getInt("uid"));
				productUser.setU_name(rs.getString("u_name"));
				productUser.setU_phone(rs.getString("u_phone"));
				productUser.setU_address(rs.getString("u_address"));
				return productUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
