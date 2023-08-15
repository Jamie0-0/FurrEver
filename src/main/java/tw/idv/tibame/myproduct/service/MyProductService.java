package tw.idv.tibame.myproduct.service;

import java.util.List;

import tw.idv.tibame.myproduct.vo.MyProduct;

public interface MyProductService {

	List<MyProduct> showMyProduct(Integer uid);
	
	Integer deleteMyProductLike(Integer plUid, Integer plPId);
	
	List<MyProduct> selectMyProduct(Integer uid);
}
