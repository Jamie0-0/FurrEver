<<<<<<< HEAD
package product.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}
=======
package tw.idv.tibame.product.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductDAO dao;
>>>>>>> TonyYen

	public List<ProductVO> searchLatest() {
		return dao.searchLatest();
	}

<<<<<<< HEAD
	public ProductVO addPro(Integer p_m_id, String p_name,
			Integer p_price, Integer p_stock, Integer p_type,
			Integer p_class, String p_des,
			byte[] p_pic_one, byte[] p_pic_two, byte[] p_pic_three, byte[] p_pic_four) {

		ProductVO productVO = new ProductVO.Builder()
											.setP_m_id(p_m_id)
											.setP_name(p_name)
											.setP_price(p_price)
											.setP_stock(p_stock)
											.setP_type(p_type)
											.setP_class(p_class)
											.setP_des(p_des)
											.setP_status(2)
											.setP_pic_one(p_pic_one)
											.setP_pic_two(p_pic_two)
											.setP_pic_three(p_pic_three)
											.setP_pic_four(p_pic_four)
											.setP_1("")
											.setP_2("")
											.setP_3("")
											.build();
=======
	public ProductVO addPro(Integer p_m_id, String p_name, Integer p_price, Integer p_stock, Integer p_type,
			Integer p_class, String p_des, byte[] p_pic_one, byte[] p_pic_two, byte[] p_pic_three, byte[] p_pic_four) {

		ProductVO productVO = new ProductVO.Builder().setP_m_id(p_m_id).setP_name(p_name).setP_price(p_price)
				.setP_stock(p_stock).setP_type(p_type).setP_class(p_class).setP_des(p_des).setP_status(2)
				.setP_pic_one(p_pic_one).setP_pic_two(p_pic_two).setP_pic_three(p_pic_three).setP_pic_four(p_pic_four)
				.setP_1("").setP_2("").setP_3("").build();
>>>>>>> TonyYen
		dao.insert(productVO);

		return productVO;
	}

<<<<<<< HEAD
	public ProductVO updatePro(String p_name,
			Integer p_price, Integer p_stock, Integer p_type,
			Integer p_class, String p_des, Integer p_status, Integer p_id,
			byte[] p_pic_one,byte[] p_pic_two,byte[] p_pic_three,byte[] p_pic_four) {

		LocalDateTime localDateTime = LocalDateTime.now();
		ProductVO productVO = new ProductVO.Builder()
											.setP_name(p_name)
											.setP_price(p_price)
											.setP_stock(p_stock)
											.setP_type(p_type)
											.setP_class(p_class)
											.setP_des(p_des)
											.setP_status(p_status)
											.setP_pic_one(p_pic_one)
											.setP_pic_two(p_pic_two)
											.setP_pic_three(p_pic_three)
											.setP_pic_four(p_pic_four)
									        .setP_upload_time(localDateTime)
											.setP_id(p_id)
											.build();
=======
	public ProductVO updatePro(String p_name, Integer p_price, Integer p_stock, Integer p_type, Integer p_class,
			String p_des, Integer p_status, Integer p_id, byte[] p_pic_one, byte[] p_pic_two, byte[] p_pic_three,
			byte[] p_pic_four) {

		LocalDateTime localDateTime = LocalDateTime.now();
		ProductVO productVO = new ProductVO.Builder().setP_name(p_name).setP_price(p_price).setP_stock(p_stock)
				.setP_type(p_type).setP_class(p_class).setP_des(p_des).setP_status(p_status).setP_pic_one(p_pic_one)
				.setP_pic_two(p_pic_two).setP_pic_three(p_pic_three).setP_pic_four(p_pic_four)
				.setP_upload_time(localDateTime).setP_id(p_id).build();
>>>>>>> TonyYen
		dao.update(productVO);

		return productVO;
	}

	public void deletePro(Integer p_id) {
		dao.delete(p_id);
	}

	public ProductVO getOnePro(Integer p_id) {
		return dao.findByPrimaryKey(p_id);
	}
<<<<<<< HEAD
	
	public List<ProductVO> getOnePro2(Integer p_id,Integer p_status,Integer p_class) {
		return dao.findByPrimaryKey2(p_id,p_status,p_class);
=======

	public List<ProductVO> getOnePro2(Integer p_id, Integer p_status, Integer p_class) {
		return dao.findByPrimaryKey2(p_id, p_status, p_class);
>>>>>>> TonyYen
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
}
