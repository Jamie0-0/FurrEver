package tw.idv.tibame.product_fe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;

import tw.idv.tibame.product_fe.vo.Product;

public interface ProductService {

	List<Product> findAllForShopList();

	List<Product> selectByKeyWords(String how, String keywords);

	Product viewProductDetail(Integer p_id);
	
	byte[] getPicByPid(Integer p_id);

	List<String> getMsgs();

	HashMap<Integer, Integer> addToCart(String p_id, String quantity, Map<Integer, Integer> cartList);
	
	Map<Product, Integer> getCartList(Map<Integer, Integer> cartList);
	
	HashMap<Integer, Integer> updateCart(int p_id, int quantity, Map<Integer, Integer> cartList);
	
	HashMap<Integer, Integer> deleteProductInCart(int p_id, Map<Integer, Integer> cartList);

	int getCartSubTotal(Map<Integer, Integer> cartList);

	JsonArray getCartListJSON(Map<Integer, Integer> cartList);
	
	void saveCartToRedis(HashMap<Integer, Integer> cartList, int uid);

	void deleteCartItemFromRedis(HashMap<Integer, Integer> cartList, int uid, int p_id);
	
	HashMap<Integer, Integer> getCartListMapForMember(HashMap<Integer, Integer> cartList, int uid);

}
