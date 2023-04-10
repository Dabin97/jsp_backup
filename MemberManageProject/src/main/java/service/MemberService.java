package service;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberService {
	private static MemberService instance = new MemberService();

	private MemberService() {	}

	public static MemberService getInstance() {
		if(instance==null)
			instance = new MemberService();
		return instance;
	}

	public ArrayList<MemberDTO> selectAllMember() throws Exception{
		ArrayList<MemberDTO> list = MemberDAO.getInstance().selectAllMember();
		
		if(list.isEmpty())
			throw new Exception("데이터가 하나도 없습니다.");
		
		return list;
	}

	public void insertMember(MemberDTO dto) {
		MemberDAO.getInstance().insertMember(dto);
	}

	public MemberDTO selectMember(String id) {
		return MemberDAO.getInstance().selectMember(id);
	}

	public int updateMember(MemberDTO dto) {
		return MemberDAO.getInstance().updateMember(dto);
	}

	public int delelteMember(String id) {
		return MemberDAO.getInstance().deleteMember(id);
	}

	public ArrayList<MemberDTO> searchMember(String kind, String search) {
		return MemberDAO.getInstance().searchMember(kind,search);
	}

	public ArrayList<HashMap<Integer, String>> selectAllGrade() {
		return MemberDAO.getInstance().selectAllGrade();
	}

	public int deleteGrade(int gradeNo) {
		return MemberDAO.getInstance().deleteGrade(gradeNo);
	}
	
	public ArrayList<HashMap<String, Object>> selectGrade(String search) {
		return MemberDAO.getInstance().selectGrade(search);
	}

	public int insertGrade(int gradeNo, String gradeName) {
		return MemberDAO.getInstance().insertGrade(gradeNo,gradeName);
	}

	public int updateGrade(int gradeNo, String gradeName) {
		return MemberDAO.getInstance().updateGrade(gradeNo,gradeName);
	}







}
