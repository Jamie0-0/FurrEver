package tw.idv.tibame.gb.service;

import java.util.List;

import tw.idv.tibame.gb.vo.GbAndProductVO;
import tw.idv.tibame.gb.vo.GbOrderVO;
import tw.idv.tibame.gb.vo.GbVO;
import tw.idv.tibame.gb.vo.ProductAndMasterVO;

public interface GBService {
	void addGb(GbVO gbVO);

	void updateGb(GbVO gbVO);

	void deleteGb(Integer gb_id);

	GbVO getGbByPrimaryKey(Integer gb_id);

	List<GbVO> getAllGb();

	List<GbAndProductVO> getAllGbJoinProduct();

	List<GbAndProductVO> getAllGbJoinProductWithBase64();

	// product join master
	List<ProductAndMasterVO> getProductsAndMasters();

	// gb join gborder
	List<GbOrderVO> getAllGbOrdersWithGbDetails();

	boolean insertGbOrder(GbOrderVO gbOrder);

	int getTotalPNumForOrderId(int orderId);

	Integer updateGbStatusToZero(int gbId);
}