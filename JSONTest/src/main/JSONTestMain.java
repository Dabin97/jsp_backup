package main;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import dto.MemberDTO;

public class JSONTestMain {

	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("result", true); //데이터 넣기 put(key,value);
		MemberDTO dto = new MemberDTO("A0001", "123456", "홍길동", "도둑놈", 1);
		
		//dto에 담긴 내용을 json에다가 객체로 저장
		//json 객체를 하나더 생성을 해서 dto에 담긴 내용을 저장
		//dto 내용이 담긴 json 객체를 맨 처음에 생성한 json 객체에 키값으로 dto로 지정해서 저장
//		JSONObject jdto = new JSONObject();
//		jdto.put("id", dto.getId());
//		jdto.put("passwd", dto.getPasswd());
//		jdto.put("name", dto.getName());
//		jdto.put("nick", dto.getNick());
//		jdto.put("gradeNo", dto.getGradeNo());
//		jdto.put("dto", jdto);
		json.put("dto", new JSONObject(dto)); //위보다 더 간단, get메소드로 자동으로 꺼내준다.
		
		//리스트에 저장된 MemberDTO를 json에 저장
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		list.add(new MemberDTO("B0002", "1234", "AAAAA", "A1234", 1));
		list.add(new MemberDTO("B0003", "1234", "BBBBB", "B1234", 3));
		list.add(new MemberDTO("B0004", "1234", "CCCCC", "CD234", 4));
		list.add(new MemberDTO("B0005", "1234", "DDDDD", "D1234", 2));
		json.put("list", new JSONArray(list));
		
		System.out.println(json.toString());
		System.out.println(json.getJSONObject("dto").get("id")); //데이터를 꺼낼때는 이런식으로 get으로 지정해서 꺼내줘야한다.
		
	}

}
