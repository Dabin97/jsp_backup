package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APICall {
	private static APICall instance = new APICall();
	private String SERVICE_KEY = "	";
	
	private APICall() {	}

	public static APICall getInstance() { //싱글톤
		if(instance == null)
			instance = new APICall();
		return instance;
	}
	
	public String callAPI(String url, Map<String, Object> map) throws Exception {
		StringBuilder apiUrl = new StringBuilder();
		
		apiUrl.append(url+"?");
		apiUrl.append("serviceKey=" + SERVICE_KEY +"&");
		
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = URLEncoder.encode(map.get(key).toString(),"utf-8");
			apiUrl.append(key + "=" +value +"&");
		}
		apiUrl.deleteCharAt(apiUrl.length()-1); //마지막에 붙는 & 제거하기 위해서
		System.out.println(apiUrl);
		//url 작업 완료 지점
		
		URL apiURL = new URL(apiUrl.toString());
		HttpURLConnection conn = (HttpURLConnection) apiURL.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			builder.append(line);
		}
		rd.close();
		conn.disconnect();
		
		return builder.toString();		
	}
	
	
	public JSONArray getKeyWordList(String search) { //키워드 목록
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("numOfRows", 20);
		map.put("pageNo", 1);
		map.put("keyword", search);
		map.put("_type", "json");
		map.put("MobileOS", "ETC");
		map.put("MobileApp", "AppTest");
		
		HashSet<String> set = new HashSet<String>();//키워드 저장하는 set - 중복되면 안되므로 set으로 
		
		try {
			JSONObject obj = new JSONObject(callAPI("https://apis.data.go.kr/B551011/PhotoGalleryService1/gallerySearchList1", map)); //이렇게 하는 이유는 public String callAPI(String url, Map<String, Object> map) 맨위에 (url,map)으로 받기 때문
			JSONArray arr = obj.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
			
			for(int i=0;i<arr.length();i++) {
				String str = arr.getJSONObject(i).getString("galSearchKeyword");
				String[] temp = str.split(", "); //각 item이 , 로 나뉘어져 있기때문
				for(String s : temp) {
					set.add(s);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONArray result = new JSONArray(set);
		return result;
	}
	
	
	
	public JSONArray getGalleryList(String search) { //url 목록
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("numOfRows", 20);
		map.put("pageNo", 1);
		map.put("title", search);
		map.put("_type", "json");
		map.put("MobileOS", "ETC");
		map.put("MobileApp", "AppTest");
		
		ArrayList<String> list = new ArrayList<String>();
		try {
			JSONObject obj = new JSONObject(callAPI("https://apis.data.go.kr/B551011/PhotoGalleryService1/galleryDetailList1", map)); 
			JSONArray arr = obj.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
			
			for(int i=0;i<arr.length();i++) {
				String str = arr.getJSONObject(i).getString("galWebImageUrl");
				list.add(str);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONArray result = new JSONArray(list);
		return result;
	}
	
	
}
/*
 *  JSON Parse한 결과 
 *  
 *  
 *  JSONArray arr = obj.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item"); 이 아래 경로를 따라서 가져온다.
									
									
 * 
			"response":{
			"header":{
			"resultCode":"0000",
			"resultMsg":"OK"
			},
			"body":{
			"items":{
			"item":[
			{
			"galContentId":"2907526",
			"galContentTypeId":"17",
			"galTitle":"파도와 서퍼",
			"galWebImageUrl":"http://tong.visitkorea.or.kr/cms2/website/26/2907526.jpg",
			"galCreatedtime":"20221114153918",
			"galModifiedtime":"20221121161412",
			"galPhotographyMonth":"제주특별자치도 서귀포시 색달동",
			"galPhotographyLocation":"장승호",
			"galPhotographer":"?2022 대한민국 관광공모전(사진), 파도와 서퍼, 제주특별자치도 서귀포시, 제주도, 중문색달해수욕장, 중문색달해변, 바닷가, 제주 바다, 제주도 바다, 서핑, 수상레저스포츠, 해양레저스포츠, 레포츠, 여름, 드론촬영, 드론사진, 항공촬영",
			"galSearchKeyword":"177"
},

 * 
 * 
 */





