import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

// 네이버 기계번역 (Papago SMT) API 예제
public class PaPaGoEx2 {
	private static final String CLIENT_ID = "	";
	private static final String CLIENT_SECRET = "	";
	private static final String API_URL = "https://openapi.naver.com/v1/papago/n2mt";
	
	public static String papagoTranslate(String target, String text) { //따로 papagoTranslate 함수로 만들어둔다.
		String result = null;
		DataOutputStream dos = null;
		BufferedReader br = null;
		HttpURLConnection con = null;

		try {
			text = URLEncoder.encode(text, "UTF-8");
			
			 Map<String, String> requestHeaders = new HashMap<>();
		     requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
		     requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET);//header 아이디랑 비밀번호 세팅

		     URL url = new URL(API_URL);//접속정보가지고오기
		     con = (HttpURLConnection) url.openConnection(); //url객체로 변환
		     
		     con.setRequestMethod("POST"); //post로 보내기
		     for(Map.Entry<String, String> header :requestHeaders.entrySet()) { //Map.Entry 튜플로 만들기
		    	 con.setRequestProperty(header.getKey(), header.getValue());
		      }
		     String postParams = "source=ko&target=" + target + "&text=" + text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en) Params 만들기

		     	con.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션
		     	
		     	//데이터 전송
		     	dos = new DataOutputStream(con.getOutputStream());
		     	dos.write(postParams.getBytes());
		     	dos.flush(); //현재 버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼를 비운다. 
		     	
		     	//결과를 받아서 처리
		     	int responseCode = con.getResponseCode();
		     	String msg = "";
		     	
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
	            	//정상 처리 되었을때
	            	br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            }else {
	            	//에러가 발생했을때
	            	br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            
	            while(true) {
	            	String str = br.readLine();
	            	if(str == null) break;
	            	msg += str;
	            }
		     	
	            System.out.println(msg);
	            
	            JSONObject json = new JSONObject(msg); //json으로 파싱
	            
	            if(responseCode == HttpURLConnection.HTTP_OK) {
	            	result = json.getJSONObject("message").getJSONObject("result").getString("translatedText"); //번역된 문장만 꺼내기
	            }else{
	            	result = json.getString("errorMessage");
	            };
	            
	            
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			con.disconnect();
		}
		
		return result;
	}
	
    public static void main(String[] args) { //main
    	Scanner sc = new Scanner(System.in);
    	System.out.print("번역할 내용 입력 : ");
    	String text = sc.nextLine();
    	System.out.println(papagoTranslate("en", text));
    	
    }
}