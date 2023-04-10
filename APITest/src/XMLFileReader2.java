import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFileReader2 {

	public static void main(String[] args) {
				// dataset.xml 파일을 읽어서 모든 내용을 출력
				// 태그명 - 값
				FileInputStream fis;
				try {
					fis = new FileInputStream("dataset.xml");
					DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
					DocumentBuilder builder = builderFactory.newDocumentBuilder();
					Document document = builder.parse(fis);
					document.getDocumentElement().normalize();
					
					NodeList list = document.getElementsByTagName("record");
					
					for(int i=0;i<list.getLength();i++) {
						NodeList childList = list.item(i).getChildNodes();
						System.out.println(list.item(i).getNodeName());
						for(int j=0;j<childList.getLength();j++) { //for문2
							System.out.println("\t"+childList.item(j).getNodeName() + " - " + 
									childList.item(j).getTextContent());
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
