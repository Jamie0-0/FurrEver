package tw.idv.tibame.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import tw.idv.tibame.order.vo.ProductOrder;
import tw.idv.tibame.product_fe.util.JedisPoolUtil;

@Repository
public class ProductOrderDaoImpl implements ProductOrderDao {

	@Autowired
	private DataSource ds;
	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	@PersistenceContext
	private Session session;

	@Override
	public int insertProductOrder(ProductOrder productOrder) {
		session.persist(productOrder);
		return productOrder.getOrder_id();
	}


	@Override
	public ProductOrder selectByUid(int order_uid) {
		return session.find(ProductOrder.class, order_uid);
	}

	@Override
	public void deleteKeys(String uid) {

		Jedis jedis = pool.getResource();
		jedis.del("user:" + uid + ":cart.list", uid);
		jedis.close();

	}

	@Override
	public List<ProductOrder> selectAllProductOrderByUid(int uid) {
		String sql = "select order_uid, order_id, order_t from product_order where order_uid = ?;";
		var list = new ArrayList<ProductOrder>();

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductOrder productOrder = new ProductOrder();
				productOrder.setOrder_id(rs.getInt("order_id"));
				productOrder.setOrder_uid(uid);
				productOrder.setOrder_t(rs.getInt("order_t"));

				list.add(productOrder);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}


}
