package API;

import java.util.HashMap;

public class TestAPI {

	public static void main(String[] args) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("numOfRows", 20);
		map.put("pageNo", 1);
		map.put("keyword", "춘천");
		map.put("_type", "json");
		map.put("MobileOS", "ETC");
		map.put("MobileApp", "AppTest");
		
		try {
			System.out.println(APICall.getInstance().callAPI("https://apis.data.go.kr/B551011/PhotoGalleryService1/gallerySearchList1", map));
			System.out.println(APICall.getInstance().getKeyWordList("춘천"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
