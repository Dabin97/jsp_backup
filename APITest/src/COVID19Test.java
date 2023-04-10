import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class COVID19Test {
    public static void main(String[] args) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1352000/ODMS_COVID_02/callCovid02Api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "	"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("500", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("apiType","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*결과형식(xml/json)*/
        urlBuilder.append("&" + URLEncoder.encode("status_dt","UTF-8") + "=" + URLEncoder.encode("20230306", "UTF-8")); /*기준일자*/
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(conn.getInputStream());
		document.getDocumentElement().normalize();
		
		NodeList list = document.getElementsByTagName("items");
		System.out.println(list.getLength());
		for(int i=0;i<list.getLength();i++) {
			NodeList childList = list.item(i).getChildNodes();
			System.out.println(list.item(i).getNodeName());
			for(int j=0;j<childList.getLength();j++) { //for문2
				System.out.println("\t"+childList.item(j).getNodeName() + " - " + 
						childList.item(j).getTextContent());
			}
        conn.disconnect();
		}
    }
}