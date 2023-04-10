import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONFileReader {

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("data.json");
			BufferedReader br = new BufferedReader(fr); //편하게 데이터 전부 읽어오기
			StringBuilder builder = new StringBuilder(); //기존 데이터에 더하는 방식 - 부하가 적다
			
			while(true) {
				String str = br.readLine();
				if(str == null) break; //없으면 중단
				builder.append(str); //문자열+문자열
			}
			System.out.println(builder); //[] = jsonArray , {} = jsonObject
			
			//---------------------------------------------------------------------------------------------
			JSONArray arr = new JSONArray(builder.toString());
			
			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = arr.getJSONObject(i); //한건이 객체
//				System.out.println(obj.names()); 모든 키값 꺼내는 부분
				for(Object key : obj.names()) {
					System.out.println(key + " : " + obj.get(key.toString()));
				}
				System.out.println();
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
