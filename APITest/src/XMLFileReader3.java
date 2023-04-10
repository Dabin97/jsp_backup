import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFileReader3 {

	public static void main(String[] args) {
		//app_card.xml에 있는 데이터를 모두 읽어서
		//CardVO로 생성해서 읽어온 한건의 데이터들 중
		//card_type : americanexpress 인것과 금액이 2000 이상 4000 미만인 데이터만 출력
		
		FileInputStream fis;
		try {
			fis = new FileInputStream("app_card.xml");
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(fis);
			document.getDocumentElement().normalize();
			
			NodeList list = document.getElementsByTagName("record");
			
			for(int i=0;i<list.getLength();i++) {
				CardVO cvo = new CardVO(list.item(i).getChildNodes());
				if(cvo.getCardType().equals("americanexpress") && 2000 >= cvo.getPrice() && cvo.getPrice() < 4000) {
					System.out.println(cvo.toString());
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
