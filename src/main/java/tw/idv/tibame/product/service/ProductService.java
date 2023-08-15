package tw.idv.tibame.product.service;

import java.util.List;

import tw.idv.tibame.product.vo.Product;

public interface ProductService {

	List<Product> showMyProduct(Integer uid);
	
	Integer deleteMyProductLike(Integer plUid, Integer plPId);
}
