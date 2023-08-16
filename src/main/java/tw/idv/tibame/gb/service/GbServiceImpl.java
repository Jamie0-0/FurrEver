package tw.idv.tibame.gb.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.tibame.gb.dao.GBDao;
import tw.idv.tibame.gb.utils.GBDatabaseUtil;
import tw.idv.tibame.gb.vo.GbAndProductVO;
import tw.idv.tibame.gb.vo.GbOrderVO;
import tw.idv.tibame.gb.vo.GbVO;
import tw.idv.tibame.gb.vo.ProductAndMasterVO;
import tw.idv.tibame.gb.vo.ProductVO;

@Service
public class GbServiceImpl implements GBService {

	@Autowired
	private GBDao dao;

	@Override
	public void addGb(GbVO gbVO) {
		dao.insert(gbVO);
	}

	@Override
	public void updateGb(GbVO gbVO) {
		dao.update(gbVO);
	}

	@Override
	public void deleteGb(Integer gb_id) {
		dao.delete(gb_id);
	}

	@Override
	public GbVO getGbByPrimaryKey(Integer gb_id) {
		return dao.findByPrimaryKey(gb_id);

	}

	@Override
	public List<GbVO> getAllGb() {
		return dao.getAll();
	}

	@Override
	public List<GbAndProductVO> getAllGbJoinProduct() {
		return dao.getGbAndProductJoin();
	}

	// 以下圖片
	@Override
	public List<GbAndProductVO> getAllGbJoinProductWithBase64() {
		List<GbAndProductVO> gbAndProductList = dao.getGbAndProductJoin();
		for (GbAndProductVO gbAndProductVO : gbAndProductList) {
			ProductVO productVO = gbAndProductVO.getProductVO();
			if (productVO != null) {
				// 轉換圖片資料為Base64字串
				String base64PicOne = convertImageToBase64(productVO.getP_pic_one());
				String base64PicTwo = convertImageToBase64(productVO.getP_pic_two());
				String base64PicThree = convertImageToBase64(productVO.getP_pic_three());
			}
		}
		return gbAndProductList;
	}

	private String convertImageToBase64(byte[] imageBytes) {
		return Base64.getEncoder().encodeToString(imageBytes);
	}
	// 以上圖片

	@Override
	public List<ProductAndMasterVO> getProductsAndMasters() {
		return dao.getProductsAndMasters();
	}

	@Override
	public List<GbOrderVO> getAllGbOrdersWithGbDetails() {
//    	System.out.println("進入service");
		return dao.getAllGbOrdersWithGbDetails();
	}

	@Override
	public boolean insertGbOrder(GbOrderVO gbOrder) {
		return dao.insertGbOrder(gbOrder);

	}

	@Override
	public int getTotalPNumForOrderId(int orderId) {
		try (Connection conn = GBDatabaseUtil.getConnection();
				PreparedStatement stmt = conn
						.prepareStatement("SELECT SUM(gb_p_num) as countPeople FROM gb_order WHERE gb_order_id = ?")) {
			stmt.setInt(1, orderId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("countPeople");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public Integer updateGbStatusToZero(int gbId) {
		return dao.updateGbStatusToZero(gbId);
	}

}
