package tw.idv.tibame.product_fe.dao;

import tw.idv.tibame.product_fe.vo.ProductLike;

public interface ProductLikeDao {

	int insert(int pl_uid, int pl_p_id);

	int delete(int pl_uid, int pl_p_id);

	ProductLike selectByUidAndPId(int pl_uid, int pl_p_id);

}