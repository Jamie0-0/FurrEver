package tw.idv.tibame.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import tw.idv.tibame.product.dao.ProductDao;
import tw.idv.tibame.product.dao.ProductJdbcDao;
import tw.idv.tibame.product.dao.ProductLikeDao;
import tw.idv.tibame.product.service.ProductService;
import tw.idv.tibame.product.vo.Product;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductLikeDao productLikeDao;
	
	@Autowired
	private ProductJdbcDao productJdbcDao;
	
	
	@Override
	public List<Product> showMyProduct(Integer uid) {
		return productDao.findByUid(uid);
	}
	
	@Transactional
	@Override
	public Integer deleteMyProductLike(Integer plUid, Integer plPId) {
		return productLikeDao.deledeleteByplUidAndplPId(plUid, plPId);
	}
	
	public List<Product> selectMyProduct(Integer uid){
		return productJdbcDao.findMyProductByUid(uid);
	}
}
