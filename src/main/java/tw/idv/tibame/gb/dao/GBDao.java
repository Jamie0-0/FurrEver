package tw.idv.tibame.gb.dao;

import java.util.List;

import tw.idv.tibame.gb.vo.GbAndProductVO;
import tw.idv.tibame.gb.vo.GbOrderVO;
import tw.idv.tibame.gb.vo.GbVO;
import tw.idv.tibame.gb.vo.ProductAndMasterVO;
import tw.idv.tibame.gb.vo.ProductVO;

public interface GBDao {
	void insert(GbVO gbVO);

	void update(GbVO gbVO);

	void delete(Integer gb_id);

	GbVO findByPrimaryKey(Integer gb_id);

	List<GbVO> getAll();

	// 新增此方法進行 Join 查詢
	List<GbAndProductVO> getGbAndProductJoin();

	void insertProductWithImages(ProductVO product);

	List<GbAndProductVO> selectByKeyWords(String how, String keywords);

	// product join master
	List<ProductAndMasterVO> getProductsAndMasters();

	// gb join gborder
	List<GbOrderVO> getAllGbOrdersWithGbDetails();

	boolean insertGbOrder(GbOrderVO gbOrder);

	Integer updateGbStatusToZero(int gbId);
}
