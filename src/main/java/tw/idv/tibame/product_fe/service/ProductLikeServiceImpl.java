package tw.idv.tibame.product_fe.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.tibame.product_fe.dao.ProductLikeDao;
import tw.idv.tibame.product_fe.dao.ProductLikeDaoImpl;
import tw.idv.tibame.product_fe.vo.ProductLike;

@Service
public class ProductLikeServiceImpl implements ProductLikeService {
	@Autowired
	private ProductLikeDao dao;

	List<String> msgs;

	public ProductLikeServiceImpl() {
		msgs = new LinkedList<String>();
	}

	@Override
	public boolean showProductLike(int pl_uid, int pl_p_id) {
		ProductLike productLike = dao.selectByUidAndPId(pl_uid, pl_p_id);
		System.out.println(productLike);

		if (productLike != null) {
			return true;

		} else {
			return false;
		}

	}

	@Override
	public boolean manageProductLike(int pl_uid, int pl_p_id) {
		msgs.clear();
		ProductLike productLike = dao.selectByUidAndPId(pl_uid, pl_p_id);

		if (productLike == null) {
			dao.insert(pl_uid, pl_p_id);
			msgs.add("已追蹤商品");
			return false;

		} else {
			dao.delete(pl_uid, pl_p_id);
			msgs.add("已取消追蹤商品");
			return true;
		}
	}

	@Override
	public List<String> getMsgs() {
		return msgs;
	}

}
