package tw.idv.tibame.product_fe.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import redis.clients.jedis.exceptions.JedisConnectionException;
import tw.idv.tibame.product_fe.dao.ProductDao;
import tw.idv.tibame.product_fe.dao.ProductJedisDao;
import tw.idv.tibame.product_fe.dao.ProductJedisDaoImpl;
import tw.idv.tibame.product_fe.util.ProductUtil;
import tw.idv.tibame.product_fe.vo.Product;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao dao;
	List<String> msgs;
	private ProductJedisDao productJedisDao;

	public ProductServiceImpl() {
		msgs = new LinkedList<String>();
		productJedisDao = new ProductJedisDaoImpl();
	}

	@Override
	public List<Product> findAllForShopList() {
		return dao.selectForShop();
	}

	@Override
	public List<Product> selectByKeyWords(String how, String keywords) {
		if (keywords.trim() != null) {
			switch (how) {
			case "byClass":
				return dao.selectByPClass(keywords);
			case "byType":
				return dao.selectByPType(keywords);
			case "byText":
				return dao.search(keywords.trim());
			}
		}
		return null;
	}

	@Override
	public Product viewProductDetail(Integer p_id) {
		return dao.selectByPId(p_id);
	}

	@Override
	public List<String> getMsgs() {
		return msgs;
	}

	@Override
	public HashMap<Integer, Integer> addToCart(String p_id_string, String quantity_string, Map<Integer, Integer> cartList) {
		msgs.clear();

		int p_id = 0;
		int quantity = 0;

		try {
			p_id = Integer.parseInt(p_id_string);
			quantity = Integer.parseInt(quantity_string);
		} catch (NumberFormatException e) {
			msgs.add("購買數量只可為數字");
			return (HashMap<Integer, Integer>) cartList;
		}

		if (quantity == 0) {
			msgs.add("購買數量不可為0");
			return (HashMap<Integer, Integer>) cartList;
		} else if (quantity < 0) {
			msgs.add("購買數量不可為負數");
			return (HashMap<Integer, Integer>) cartList;
		}

		/////// 先檢查欲增加數量有沒有大於商品庫存數量!
		int p_stock = dao.selectPStockByPid(p_id);
		if (quantity > p_stock) {
			msgs.add("購買數量不可超過商品數量!");
			return (HashMap<Integer, Integer>) cartList;
		}

		boolean productExists = false;
//		// 如果這個session沒有購物車清單, 給一個新的空的購物車清單
		if (cartList == null) {
			cartList = new HashMap<Integer, Integer>();
			cartList.put(p_id, quantity);
			
			HashMap<Integer, Integer> newCartList = (HashMap<Integer, Integer>) cartList;
			System.out.println("原本購物車為空, addToCart方法內的購物車cartListmap = " + cartList);
			return newCartList;
		}

		// 若有原本購物車有東西, 取出購物車清單內所有p_id_in_cart
		Set<Integer> cartListKeys = cartList.keySet();

		Iterator it = cartListKeys.iterator();
		while (it.hasNext()) {
			int p_id_in_cart = (int) it.next();
			System.out.println("購物車中原本的商品id : p_id_in_cart =" + p_id_in_cart);

			if (p_id == p_id_in_cart) {
				// 若購物車已有該商品, 則只修改數量
				// 取出原本購物車中該商品數量
				int p_quantity_in_cart = cartList.get(p_id_in_cart);
				System.out.println("購物車中原本的該商品數量 : p_quantity_in_cart =" + p_quantity_in_cart);

				if ((p_quantity_in_cart + quantity) > p_stock) {
					msgs.add("購買數量不可超過商品數量!");
					return (HashMap<Integer, Integer>) cartList;
				}
				cartList.put(p_id, (p_quantity_in_cart + quantity));
				productExists = true;
				break;
			} else if (p_id != p_id_in_cart) {
				continue;
			}
		}

		if (productExists == false) {
			// 若購物車尚無該商品, 則增加商品及數量
			cartList.put(p_id, quantity);
		}
		
		HashMap<Integer, Integer> newCartList = (HashMap<Integer, Integer>) cartList;

		System.out.println("原本購物車有東西, 更新後addToCart方法內的購物車cartListmap = " + cartList);

		return newCartList;
	}
	

	@Override
	public Map<Product, Integer> getCartList(Map<Integer, Integer> cartList) {

		msgs.clear();

		Map<Product, Integer> cartListMap = new HashMap<Product, Integer>();

		if (cartList == null || cartList.isEmpty()) {
			msgs.add("您的購物車是空的");
			return null;
		}
		System.out.println("getCartList方法內的購物車cartListmap = " + cartList);

//		若有清單, 取出購物車清單內所有p_id (p_id_in_cart)
		Set<Integer> cartListkeys = cartList.keySet();
		System.out.println("getCartList方法內的購物車cartListkeys = " + cartListkeys);

		Iterator it = cartListkeys.iterator();
		while (it.hasNext()) {
			// 拿商品資訊(key)
			int p_id = (int) it.next();
			System.out.println("getCartList方法內的購物車p_id = " + p_id);
			// 拿購買數量(value)
			int quantity = cartList.get(p_id);
			System.out.println("getCartList方法內的購物車quantity = " + quantity);
			Product product = dao.selectByPId(p_id);
			cartListMap.put(product, quantity);
		}

		return cartListMap;
	}

	@Override
	public HashMap<Integer, Integer> updateCart(int p_id, int quantity, Map<Integer, Integer> cartList) {
		msgs.clear();

		/////// 先檢查欲增加數量有沒有大於商品庫存數量!
		int p_stock = dao.selectPStockByPid(p_id);
		if (quantity > p_stock) {
			msgs.add("購買數量不可超過商品數量!");
			return (HashMap<Integer, Integer>) cartList;
		}

		cartList.put(p_id, quantity);
		
		HashMap<Integer, Integer> newCartList = (HashMap<Integer, Integer>) cartList;

		System.out.println("原本購物車有東西, 更新後updateCart方法內的購物車cartListmap = " + cartList);

		return newCartList;
	}
	

	@Override
	public HashMap<Integer, Integer> deleteProductInCart(int p_id, Map<Integer, Integer> cartList) {
		msgs.clear();

		Integer isRemoved = cartList.remove(p_id);

		if (isRemoved != null) {
			System.out.println("原本購物車有東西, 更新後deleteCart方法內的購物車cartListmap = " + cartList);
			return (HashMap<Integer, Integer>) cartList;
		} else {
			System.out.println("刪除購物車商品失敗");
			return (HashMap<Integer, Integer>) cartList;
		}


	}

	@Override
	public int getCartSubTotal(Map<Integer, Integer> cartList) {
		msgs.clear();

		int subtotal = 0;

		// 計算所有商品小計(不含運費)
		Set<Integer> cartListKeys = cartList.keySet();

		Iterator it = cartListKeys.iterator();
		while (it.hasNext()) {
			int p_id_in_cart = (int) it.next();
			int p_quantity = cartList.get(p_id_in_cart);
			int p_price = dao.selectByPId(p_id_in_cart).getP_price();

			subtotal += p_quantity * p_price;
		}

		return subtotal;
	}

	@Override
	public JsonArray getCartListJSON(Map<Integer, Integer> cartList) {
		msgs.clear();

		Map<Product, Integer> cartListMap = new HashMap<Product, Integer>();

		if (cartList == null || cartList.isEmpty()) {
			msgs.add("您的購物車是空的");
			return null;
		}

		System.out.println("getCartList方法內的購物車cartListmap = " + cartList);

//		若有清單, 取出購物車清單內所有p_id (p_id_in_cart)
		Set<Integer> cartListkeys = cartList.keySet();
		System.out.println("getCartList方法內的購物車cartListkeys = " + cartListkeys);

		Iterator it = cartListkeys.iterator();
		while (it.hasNext()) {
			// 拿商品id(key)
			int p_id_in_cart = (int) it.next();
			System.out.println("getCartList方法內的購物車p_id = " + p_id_in_cart);
			// 拿購買數量(value)
			int quantity = cartList.get(p_id_in_cart);
			System.out.println("getCartList方法內的購物車quantity = " + quantity);
			Product product = dao.selectByPId(p_id_in_cart);
			// 商品對應的購買數量放入map
			cartListMap.put(product, quantity);
		}

		// 將購物車內容轉換成 JSON 物件的陣列並回傳給前端
		JsonArray cartArray = new JsonArray();

		for (Map.Entry<Product, Integer> entry : cartListMap.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			JsonObject cartItem = new JsonObject();
			cartItem.addProperty("p_id", product.getP_id());
			cartItem.addProperty("p_m_id", product.getP_m_id());
			cartItem.addProperty("p_name", product.getP_name());
			cartItem.addProperty("p_price", product.getP_price());
			cartItem.addProperty("quantity", quantity);
			cartArray.add(cartItem);
		}

		return cartArray;

	}

	@Override
	public void saveCartToRedis(HashMap<Integer, Integer> cartList, int uid) {
		try {
			Map<String, String> cartListString = ProductUtil.mapIntToString(cartList);
			productJedisDao.setCartList(cartListString, uid);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
			System.out.println("Redis連線失敗");
		}

	}

	@Override
	public void deleteCartItemFromRedis(HashMap<Integer, Integer> cartList, int uid, int p_id) {
		String pid = String.valueOf(p_id);

		try {
			Map<String, String> cartListString = ProductUtil.mapIntToString(cartList);
			productJedisDao.deleteCartItem(cartListString, uid, pid);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
			System.out.println("Redis連線失敗");
		}

	}

	@Override
	public HashMap<Integer, Integer> getCartListMapForMember(HashMap<Integer, Integer> cartList, int uid) {

		Map<String, String> reddisCartList = productJedisDao.getReddisCartList(uid);

		if (!reddisCartList.isEmpty()) {
			cartList = ProductUtil.mapStringCastToInt(reddisCartList);
		}

		return cartList;
	}

	@Override
	public byte[] getPicByPid(Integer p_id) {

		byte[] pic = dao.selectPicByPid(p_id);

		if (pic == null) {
			System.out.println("商品圖片抓取失敗");
			return null;
		} else {
			return pic;
		}
	}

}
