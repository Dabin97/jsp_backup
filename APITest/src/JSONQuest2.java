import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONQuest2 {

	public static void main(String[] args) {
		//city.list.json 파일 읽어와서
		//우리나라 도시만 좌표 정보와 도시명을 출력
		// Seoul : 위도, 경도
		
		try {
			byte[] encode = Files.readAllBytes(Paths.get("city.list.json")); //용량이 커서 느리면 바이트단위로 읽어들여서 문자열로 바꾼다.
			String r = new String(encode);
			
			//---------------------------------------------------------------------------------------------
			JSONArray arr = new JSONArray(r);
			
			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = arr.getJSONObject(i); 
//				System.out.println(obj.toString()); 전부 뽑기
				if ( obj.getString("country").equals("KR")) {
					System.out.println(obj.getString("name") + " : " 
				+ obj.getJSONObject("coord").getDouble("lon") + "," 
				+ obj.getJSONObject("coord").getDouble("lat"));
				}
			}
			
			/*
			 * {
		        "id": 1835847,
		        "name": "Seoul",
		        "state": "",
		        "country": "KR",
		        "coord": {
		            "lon": 127.0,  <-- double로 뽑는다.
		            "lat": 37.583328
				        }
				    },
			 * 
			 */
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
