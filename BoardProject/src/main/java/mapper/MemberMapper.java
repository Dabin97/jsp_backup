package mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import config.DBManager;
import dto.MemberDTO;

public class MemberMapper {

	private static MemberMapper instance = new MemberMapper();
	private SqlSession session;
	private MemberMapper() {
		session = DBManager.getInstance().getSession();
		
	}

	public static MemberMapper getInstance() {
		if(instance==null)
			instance = new MemberMapper();
		return instance;
	}

	public MemberDTO login(Map<String, Object> map) {
		MemberDTO dto = session.selectOne("login", map);
		return dto;
	}

	public List<MemberDTO> selectAllMember() {
		return session.selectList("selectAllMember");
	}

	public MemberDTO selectMember(String id) {
		return session.selectOne("selectMember",id);
	}

	public int updateMember(MemberDTO dto) {
		return session.update("updateMember", dto);
	}

	public int deleteMember(String id) {
		return session.delete("deleteMember", id);
	}

	public int insertMember(MemberDTO dto) {
		return session.insert("insertMember", dto);
	}

	public List<MemberDTO> searchMember(HashMap<String, Object> map) {
		return session.selectList("searchMember",map);
	}


}
