import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

//뉴스와 블로그는 똑같은 형식 - 싱글톤 적용

public class NaverNewsRun {
	private static NaverNewsRun instance = new NaverNewsRun();
	public NaverNewsRun() {	}

	public static NaverNewsRun getInstance() { //싱글톤
		if(instance == null)
			instance = new NaverNewsRun();
		return instance;
	}

	private static final String CLIENT_ID = "	"; 
	private static final String CLIENT_SECRET = "	"; 
	private static final String API_URL = "https://openapi.naver.com/v1/search/news.json?query="; 
	
	public static String searchNews(String text) {
		String result = null; //결과변수 미리 선언
		BufferedReader br = null; //읽어오기
		HttpURLConnection con = null; //HttpURLConnection는 HTTP 통신을 가능케 해주는 클래스
		
	        try {
	            text = URLEncoder.encode(text, "UTF-8");
	            
	            Map<String, String> requestHeaders = new HashMap<>();
	            requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
	            requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET); //여기까지 헤더 셋팅
	            
	            URL url = new URL(API_URL+text); //url뒤에 검색어가 붙음
	            con = (HttpURLConnection)url.openConnection(); //con에 값넣고
	            
	            con.setRequestMethod("GET"); // 요청 방식 선택 (GET)
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }
	            
	            con.setDoOutput(true); // OutputStream으로 get 데이터를 넘겨주겠다는 옵션.
	            
	            //결과를 받아서 처리
	            int responseCode = con.getResponseCode();
	            String msg = ""; //메세지변수
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                // return readBody(con.getInputStream()); 예제에 있던것, readBody변수를 아예 지웠다.
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else { // 오류 발생
	            	br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            } 
	            
	            while(true) { //받은 내용 메세지 안에 넣기
	            	String str = br.readLine();
	            	if(str == null) break;
	            	msg += str;
	            }
	            
	            System.out.println(msg);//확인용
	            
	            //파일 생성 여기부터
		        text = URLDecoder.decode(text, "utf-8");
	            String fileName = "news" + "_" + text + ".html"; 
	            
	            JSONObject json = new JSONObject(msg); //받은 내용을 json으로 파싱
	            
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	            	FileWriter fw = new FileWriter(fileName);
	            	PrintWriter pw = new PrintWriter(fw);
	            	
	            	JSONArray arr = json.getJSONArray("items"); //jsonarray로 만들어서 내용을 하나씩 뽑기
	            	result = new String();
	            	for (int i = 0; i < arr.length(); i++) {
						pw.println("<p>");
						//JSON객체를 하나 읽어옴
						JSONObject obj = arr.getJSONObject(i);
						result += obj.getString("title") + "\n"; //받아온 내용을 하나씩 뽑아서 result안에 넣음, 이 내용들이 다 출력됨
						result += obj.getString("link") + "\n";  //json parser를 이용해서 읽어보면 items안에 해당 내용들이 들어있음, 그걸 하나씩 뽑는것
						result += obj.getString("description") + "\n"; 
						result += obj.getString("originallink") + "\n";
						result += obj.getString("link") + "\n";
						result += obj.getString("pubDate") + "\n";
						result += "----------------------------------\n"; //여기서 끊는듯
						pw.println("<p>" + obj.getString("title") + "</p>"); 
						pw.println("<p><a href=" + obj.getString("link") + ">해당 페이지 이동 </a></p>");//링크에 a태그 걸기
						pw.println("<p>" + obj.getString("description") + "</p>");  //
						pw.println("</p><hr>");
						pw.flush(); //출력 - result에 내용을 하나씩 다 넣고, 제목, 링크, 설명만 출력함
					}
	            	pw.close(); //닫아주기
	            } else { // 오류 발생
	            	result = json.getString("errorMessage");
	            	PrintStream ps = null;
	                FileOutputStream fos=null;
                    File file = new File("C:\\excetion.txt"); 
                    fos = new FileOutputStream(file,true); 
                    ps=new PrintStream(fos); 
                    System.setErr(ps); 
                    System.err.println("예외메시지 : " + result);
	            }  
	        } catch (UnsupportedEncodingException e) { 
	        	e.printStackTrace();
	        } catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace(); //Exception은 뜨는대로 잡아주기, 그래야 어디서 오류가 났는지 정확하게 알수있다.
			}
		return result; 
	} 
	
	
}
