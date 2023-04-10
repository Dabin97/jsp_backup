package mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import config.DBManager;

public class GradeMapper {
	private static GradeMapper instance = new GradeMapper();
	private SqlSession session;
	private GradeMapper() {	
		session = DBManager.getInstance().getSession();
	}

	public static GradeMapper getInstance() {
		if(instance == null)
			instance = new GradeMapper();
		return instance;
	}

	public List<Map<String, Object>> selectAllGrade() {
		return session.selectList("selectAllGrade");
	}

	public int insertGrade(Map<String, Object> map) {
		return session.insert("insertGrade", map);
	}

	public List<HashMap<String, Object>> selectGrade(String search) {
		return session.selectList("selectGrade",search);
	}

	public int updateGrade(HashMap<String, Object> map) {
		return session.update("updateGrade", map);
	}

	public int deleteGrade(int gradeNo) {
		return session.delete("deleteGrade", gradeNo);
	}

	
	
	
	
}
