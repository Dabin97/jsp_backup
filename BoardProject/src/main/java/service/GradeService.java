package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mapper.GradeMapper;

public class GradeService {
	private static GradeService instance = new GradeService();
	
	private GradeService() {	}

	public static GradeService getInstance() {
		if(instance == null)
			instance = new GradeService();
		return instance;
	}

	public List<Map<String, Object>> selectAllGrade() {
		return GradeMapper.getInstance().selectAllGrade();
	}

	public int insertGrade(int gradeNo, String gradeName) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("grade_no", gradeNo);
		map.put("grade_name", gradeName);
		return GradeMapper.getInstance().insertGrade(map);

	}

	public List<HashMap<String, Object>> selectGrade(String search) {
		return GradeMapper.getInstance().selectGrade(search);
	}

	public int updateGrade(int gradeNo, String gradeName) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("grade_no", gradeNo);
		map.put("grade_name", gradeName);
		return GradeMapper.getInstance().updateGrade(map);
	}

	public int deleteGrade(int gradeNo) {
		return GradeMapper.getInstance().deleteGrade(gradeNo);
	}
	
	
	
	
	
}
