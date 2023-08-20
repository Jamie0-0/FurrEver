package tw.idv.tibame.product_fe.util;

import java.util.HashMap;
import java.util.Map;

public class ProductUtil {

	public static HashMap<Integer, Integer> mapStringCastToInt(Map<String, String> stringMap) {
		HashMap<Integer, Integer> intMap = new HashMap<>();

		if (stringMap != null) {
			for (HashMap.Entry<String, String> entry : stringMap.entrySet()) {
				String stringKey = entry.getKey();
				String stringValue = entry.getValue();

				try {
					int keyInt = Integer.parseInt(stringKey);
					int valueInt = Integer.parseInt(stringValue);

					intMap.put(keyInt, valueInt);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("轉型成int購物車失敗");
				}
			}
			System.out.println("轉完的int購物車=" + intMap);

		}

		return intMap;

	}

	public static Map<String, String> mapIntToString(HashMap<Integer, Integer> intMap) {
		Map<String, String> stringMap = new HashMap<>();

		if (stringMap != null) {
			for (HashMap.Entry<Integer, Integer> entry : intMap.entrySet()) {
				int intKey = entry.getKey();
				int intValue = entry.getValue();

				try {
					String stringKey = String.valueOf(intKey);
					String stringValue = String.valueOf(intValue);
					stringMap.put(stringKey, stringValue);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("轉型成string購物車失敗");
				}
			}
			System.out.println("轉完的string購物車=" + stringMap);

		}

		return stringMap;

	}
}
