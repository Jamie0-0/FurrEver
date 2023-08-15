package tw.idv.tibame.product_fe.dao;

import tw.idv.tibame.product_fe.vo.ProductUser;

public interface ProductUserDao {

	ProductUser selectByUserName(String name); // 原

	ProductUser selectByUserNameForCart(String name); // 原

	ProductUser selectByUidForCart(int uid);

}