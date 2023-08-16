package tw.idv.tibame.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.idv.tibame.weimaster.model.MasterPicVO;
import tw.idv.tibame.weimaster.model.MasterPicVO2;

@Repository
public class ProductDAO implements ProductDAO_interface {
	@Autowired
	private DataSource ds;

//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tha102");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	private String INSERT_STMT = "INSERT INTO product (p_m_id,p_name,p_price,p_stock,p_type,p_class,p_des,p_status,p_1,p_2,p_3,p_upload_time,p_pic_one,p_pic_two,p_pic_three,p_pic_four) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";;
	private static final String GET_ONE_STMT = "SELECT * FROM product where p_id = ? and p_m_id=?";
	private String GET_ONE_STMT2 = "SELECT * FROM FurrEver.product";
	private static final String DELETE = "DELETE FROM product where p_id=? and p_m_id=?";
	private String UPDATE = "UPDATE product set p_name=?,p_price=?,p_stock=?,p_type=?,p_class=?,p_des=?,p_status=?, p_upload_time=?";
	private static final String GET_ALL_STMT = "SELECT * FROM product where p_m_id=? order by p_id";
	private static final String GET_NEW_PRO = "SELECT * FROM product order by p_upload_time desc limit 1";
	private static final String GET_NEW_info = "select sum(a) as 'a',sum(b) as 'b',sum(c) as 'c',sum(d) as 'd' \r\n"
			+ "from (\r\n"
			+ "SELECT sum(order_num) as 'a',count(*) as 'b' ,0 as 'c',0 as 'd'\r\n"
			+ "FROM FurrEver.sub_order\r\n"
			+ "where so_m_id = ?\r\n"
			+ "union \r\n"
			+ "SELECT 0 as 'a',0 as 'b',count(*) as 'c',0 as 'd'\r\n"
			+ "FROM FurrEver.product\r\n"
			+ "where p_m_id = ?\r\n"
			+ "union\r\n"
			+ "select 0 as 'a',0 as 'b',0 as 'c',count(distinct order_r_name) as 'd'\r\n"
			+ "FROM FurrEver.product_order a,FurrEver.sub_order b\r\n"
			+ "where a.order_id = b.so_order_id\r\n"
			+ "and so_m_id = ?\r\n"
			+ ") as allvalue\r\n";
	
	private static final String GET_TOP_PRO = "SELECT p_name,sum(p_m_stock) as 'a',sum(p_m_price)as 'b',p_pic_one \r\n"
			+ "FROM FurrEver.sub_order,FurrEver.sub_product,FurrEver.product\r\n"
			+ "where so_order_id = order_id\r\n"
			+ "and p_p_id = p_id\r\n"
			+ "and p_m_id = ?\r\n"
			+ "group by p_p_id\r\n"
			+ "order by 2 desc limit 3";
	
	
	private static final String GET_TOP_ORD = "	SELECT order_r_name,order_r_phone,order_r_addr,order_t,order_pay,u_pic\r\n"
			+ "	FROM FurrEver.product_order,FurrEver.sub_order,user\r\n"
			+ "	where order_id = so_order_id\r\n"
			+ " and so_m_id = ?\r\n"
			+ "	and uid = order_uid\r\n"
			+ "	order by order_id limit 3";

    @Override
    public ProductVO indexValue(Integer p_m_id) {
    	ProductVO productVO = null;
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NEW_info);

			// 這裡是寫每個欄位的輸入
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 1);
			pstmt.setInt(3, 1);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				productVO = new ProductVO.Builder()
											.setA(rs.getInt(1))
											.setB(rs.getInt(2))
											.setC(rs.getInt(3))
											.setD(rs.getInt(4))
											.build();
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
    	return productVO;
    }
    
    @Override
    public List<MasterPicVO> indexNatrix1(Integer p_m_id){
    	List<MasterPicVO> list = new ArrayList<MasterPicVO>();
    	MasterPicVO masterPicVO = null;

    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TOP_PRO);

			// 這裡是寫每個欄位的輸入
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String p1 = "";
				if(rs.getBytes(4) != null) {
					p1 = Base64.getEncoder().encodeToString(rs.getBytes(4));
				}				

				masterPicVO = new MasterPicVO.Builder()
											.setP_name(rs.getString(1))
											.setB(rs.getInt(2))
											.setC(rs.getInt(3))
											.setP_pic_one(p1)
											.build();

				list.add(masterPicVO);
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

    @Override
    public List<MasterPicVO2> indexNatrix2(Integer mid){
    	List<MasterPicVO2> list = new ArrayList<MasterPicVO2>();
    	MasterPicVO2 masterPicVO2 = null;
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TOP_ORD);
			pstmt.setInt(1, mid);
			rs = pstmt.executeQuery();
			
			
			

			while (rs.next()) {
				String p1 = "";
				if(rs.getBytes(6) != null) {
					p1 = Base64.getEncoder().encodeToString(rs.getBytes(6));
				}	
				masterPicVO2 = new MasterPicVO2.Builder()
												.setOrder_r_name(rs.getString(1))
												.setOrder_r_phone(rs.getString(2))
												.setOrder_r_addr(rs.getString(3))
												.setOrder_t(rs.getString(4))
												.setOrder_pay(rs.getInt(5))
												.setU_pic(p1)
												.build();
				list.add(masterPicVO2);
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
	
	
	
	@Override
	public List<ProductVO> searchLatest() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NEW_PRO);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				// 這裡是查到的值，須將所有欄位值放進去
				productVO = new ProductVO.Builder()
											.setP_id(rs.getInt("p_id"))
											.setP_m_id(rs.getInt("p_m_id"))
											.setP_name(rs.getString("p_name"))
											.setP_price(rs.getInt("p_price"))
											.setP_stock(rs.getInt("p_stock"))
											.setP_count(rs.getInt("p_count"))
											.setP_type(rs.getInt("p_type"))
											.setP_class(rs.getInt("p_class"))
											.setP_upload_time(rs.getTimestamp("p_upload_time").toLocalDateTime())
											.setP_des(rs.getString("p_des"))
											.setP_status(rs.getInt("p_status"))
											.setP_pic_one(rs.getBytes("p_pic_one"))
											.setP_pic_two(rs.getBytes("p_pic_two"))
											.setP_pic_three(rs.getBytes("p_pic_three"))
											.setP_pic_four(rs.getBytes("p_pic_four"))
											.setP_1(rs.getString("p_1"))
											.setP_2(rs.getString("p_2"))
											.setP_3(rs.getString("p_3"))
											.build();

				list.add(productVO);
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

	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			// 這裡是寫每個欄位的輸入
			pstmt.setInt(1, productVO.getP_m_id());
			pstmt.setString(2, productVO.getP_name());
			pstmt.setInt(3, productVO.getP_price());
			pstmt.setInt(4, productVO.getP_stock());
			pstmt.setInt(5, productVO.getP_type());
			pstmt.setInt(6, productVO.getP_class());
			pstmt.setString(7, productVO.getP_des());
			pstmt.setInt(8, 2);//要等於2
			pstmt.setString(9, productVO.getP_1());
			pstmt.setString(10, productVO.getP_2());
			pstmt.setString(11, productVO.getP_3());
			LocalDateTime localDateTime = LocalDateTime.now();
			pstmt.setObject(12, localDateTime);

			pstmt.setBytes(13, productVO.getP_pic_one());
			pstmt.setBytes(14, productVO.getP_pic_two());
			pstmt.setBytes(15, productVO.getP_pic_three());
			pstmt.setBytes(16, productVO.getP_pic_four());

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
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			
			ArrayList imgArr = new ArrayList();
			if(productVO.getP_pic_one().length > 0) {
				UPDATE = UPDATE+", p_pic_one=?";
				imgArr.add("one");
			}
			
			if(productVO.getP_pic_two().length > 0) {
				UPDATE = UPDATE+", p_pic_two=?";
				imgArr.add("two");
			}
			
			if(productVO.getP_pic_three().length > 0) {
				UPDATE = UPDATE+", p_pic_three=?";
				imgArr.add("three");
			}

			if(productVO.getP_pic_four().length > 0) {
				UPDATE = UPDATE+", p_pic_four=?";
				imgArr.add("four");
			}
			UPDATE = UPDATE+" where p_id=? and p_m_id=?";
			pstmt = con.prepareStatement(UPDATE);

			// 這裡是寫每個欄位的輸入
			pstmt.setString(1, productVO.getP_name());
			pstmt.setInt(2, productVO.getP_price());
			pstmt.setInt(3, productVO.getP_stock());
			pstmt.setInt(4, productVO.getP_type());
			pstmt.setInt(5, productVO.getP_class());
			pstmt.setString(6, productVO.getP_des());
			pstmt.setInt(7, productVO.getP_status());
			LocalDateTime localDateTime = LocalDateTime.now();
			pstmt.setObject(8, localDateTime);
			for(int i = 0;i < imgArr.size();i ++) {
				if(imgArr.get(i).equals("one")) {
					pstmt.setBytes(9+i, productVO.getP_pic_one());
				} else if(imgArr.get(i).equals("two")) {
					pstmt.setBytes(9+i, productVO.getP_pic_two());
				} else if(imgArr.get(i).equals("three")) {
					pstmt.setBytes(9+i, productVO.getP_pic_three());
				} else if(imgArr.get(i).equals("four")) {
					pstmt.setBytes(9+i, productVO.getP_pic_four());
				}
			}
			pstmt.setInt(9+imgArr.size(), productVO.getP_id());
			pstmt.setInt(10+imgArr.size(), productVO.getP_m_id());
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
	public void delete(Integer getP_id,Integer mid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			// 這裡是刪除功能，因為getM_id是key值，只要輸入下面一句話，就完成這一整段
			pstmt.setInt(1, getP_id);
			pstmt.setInt(2, mid);
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
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO.Builder()
											.setP_id(rs.getInt("p_id"))
											.setP_m_id(rs.getInt("p_m_id"))
											.setP_name(rs.getString("p_name"))
											.setP_price(rs.getInt("p_price"))
											.setP_stock(rs.getInt("p_stock"))
											.setP_count(rs.getInt("p_count"))
											.setP_type(rs.getInt("p_type"))
											.setP_class(rs.getInt("p_class"))
											.setP_upload_time(rs.getTimestamp("p_upload_time").toLocalDateTime())
											.setP_des(rs.getString("p_des"))
											.setP_status(rs.getInt("p_status"))
											.setP_pic_one(rs.getBytes("p_pic_one"))
											.setP_pic_two(rs.getBytes("p_pic_two"))
											.setP_pic_three(rs.getBytes("p_pic_three"))
											.setP_pic_four(rs.getBytes("p_pic_four"))
											.setP_1(rs.getString("p_1"))
											.setP_2(rs.getString("p_2"))
											.setP_3(rs.getString("p_3"))
											.build();

				list.add(productVO);
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
	
	@Override
	public ProductVO findByPrimaryKey(Integer p_id,Integer mid) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, p_id);
			pstmt.setInt(2, mid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				// 這裡是查到的值，須將所有欄位值放進去
				productVO = new ProductVO.Builder()
											.setP_id(rs.getInt("p_id"))
											.setP_m_id(rs.getInt("p_m_id"))
											.setP_name(rs.getString("p_name"))
											.setP_price(rs.getInt("p_price"))
											.setP_stock(rs.getInt("p_stock"))
											.setP_count(rs.getInt("p_count"))
											.setP_type(rs.getInt("p_type"))
											.setP_class(rs.getInt("p_class"))
											.setP_upload_time(rs.getTimestamp("p_upload_time").toLocalDateTime())
											.setP_des(rs.getString("p_des"))
											.setP_status(rs.getInt("p_status"))
											.setP_pic_one(rs.getBytes("p_pic_one"))
											.setP_pic_two(rs.getBytes("p_pic_two"))
											.setP_pic_three(rs.getBytes("p_pic_three"))
											.setP_pic_four(rs.getBytes("p_pic_four"))
											.setP_1(rs.getString("p_1"))
											.setP_2(rs.getString("p_2"))
											.setP_3(rs.getString("p_3"))
											.build();

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
		return productVO;
	}

	@Override
	public List<ProductVO> findByPrimaryKey2(Integer p_id,Integer p_status,Integer p_class,Integer mid) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ArrayList imgArr = new ArrayList();
			if(p_id>0) {
				imgArr.add("id");
			}
			if(p_status>0) {
				imgArr.add("status");
			}
			if(p_class>0) {
				imgArr.add("class");
			}

			for(int i = 0;i<imgArr.size();i++) {
				if(i == 0) {
					if(imgArr.get(i).equals("id")) {
						GET_ONE_STMT2 = GET_ONE_STMT2+" where p_id = ?";
					} else if(imgArr.get(i).equals("status")) {
						GET_ONE_STMT2 = GET_ONE_STMT2+" where p_status = ?";
					} else if(imgArr.get(i).equals("class")) {
						GET_ONE_STMT2 = GET_ONE_STMT2+" where p_class = ?";
					}
				} else {
					if(imgArr.get(i).equals("id")) {
						GET_ONE_STMT2 = GET_ONE_STMT2+" and p_id = ?";
					} else if(imgArr.get(i).equals("status")) {
						GET_ONE_STMT2 = GET_ONE_STMT2+" and p_status = ?";
					} else if(imgArr.get(i).equals("class")) {
						GET_ONE_STMT2 = GET_ONE_STMT2+" and p_class = ?";
					}
				}
			}
			GET_ONE_STMT2 = GET_ONE_STMT2+" and p_m_id = ?";
			pstmt = con.prepareStatement(GET_ONE_STMT2);

			for(int i = 0;i<imgArr.size();i++) {
				if(imgArr.get(i).equals("id")) {
					pstmt.setInt(i+1, p_id);
				} else if(imgArr.get(i).equals("class")) {
					pstmt.setInt(i+1, p_class);
				} else if(imgArr.get(i).equals("status")) {
					pstmt.setInt(i+1, p_status);
				}
				
				if(i == imgArr.size()-1) {
					pstmt.setInt(i+2, mid);
				}
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 這裡是查到的值，須將所有欄位值放進去
				productVO = new ProductVO.Builder()
										.setP_id(rs.getInt("p_id"))
										.setP_m_id(rs.getInt("p_m_id"))
										.setP_name(rs.getString("p_name"))
										.setP_price(rs.getInt("p_price"))
										.setP_stock(rs.getInt("p_stock"))
										.setP_count(rs.getInt("p_count"))
										.setP_type(rs.getInt("p_type"))
										.setP_class(rs.getInt("p_class"))
										.setP_upload_time(rs.getTimestamp("p_upload_time").toLocalDateTime())
										.setP_des(rs.getString("p_des"))
										.setP_status(rs.getInt("p_status"))
										.setP_pic_one(rs.getBytes("p_pic_one"))
										.setP_pic_two(rs.getBytes("p_pic_two"))
										.setP_pic_three(rs.getBytes("p_pic_three"))
										.setP_pic_four(rs.getBytes("p_pic_four"))
										.setP_1(rs.getString("p_1"))
										.setP_2(rs.getString("p_2"))
										.setP_3(rs.getString("p_3"))
										.build();

				list.add(productVO);
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
