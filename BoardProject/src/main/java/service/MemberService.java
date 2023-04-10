package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.MemberDTO;
import mapper.MemberMapper;

public class MemberService {
	private static MemberService instance = new MemberService();
	private MemberMapper mapper;
	private MemberService() {	
		mapper = MemberMapper.getInstance();
	}

	public static MemberService getInstance() {
		if(instance==null)
			instance = new MemberService();
		return instance;
	}

	public MemberDTO login(String id, String passwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id",id);
		map.put("passwd",passwd);
		return mapper.login(map);
	}

	public List<MemberDTO> selectAllMember() {
		return mapper.selectAllMember();
	}

	public MemberDTO selectMember(String id) {
		return mapper.selectMember(id);
	}

	public int updateMember(MemberDTO dto) {
		return mapper.updateMember(dto);
	}

	public int delelteMember(String id) {
		return mapper.deleteMember(id);
	}

	public int insertMember(MemberDTO dto) {
		return mapper.insertMember(dto);
	}

	public List<MemberDTO> searchMember(String kind, String search) {
		HashMap<String, Object> map = new HashMap<String, Object>(); //여러개면 map으로 만들어서 보낸다.
		map.put("kind", kind);
		return mapper.searchMember(map);
	}


}