package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.json.JSONObject;

import config.DBManager;
import dto.MemberDTO;
import service.MemberService;

public class MemberDAO {
	//싱글톤 패턴
		private static MemberDAO instance = new MemberDAO();
		private DBManager manager;
		
		private MemberDAO() {
			manager = DBManager.getInstance();
		}

		public static MemberDAO getInstance() {
			if(instance == null)
				instance = new MemberDAO();
			return instance;
		}

		public ArrayList<MemberDTO> selectAllMember(){
			//전체 회원정보를 DB에서 조회해서 ArrayList로 리턴
			//1. SQL문 작성
			String sql = "select * from board_member";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			try {
				//2. PreparedStatement 생성
				pstmt = manager.getConn().prepareStatement(sql);
				//3. pstmt 실행
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String id = rs.getString("id");
					String passwd = rs.getString(2);
					String name = rs.getString(3);
					String nick = rs.getString(4);
					int gradeNo = rs.getInt(5);
					list.add(new MemberDTO(id, passwd, name, nick, gradeNo));				
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}
		

		public int insertMember(MemberDTO dto) {
			int count = 0;
			
			String sql = "insert into board_member values (?,?,?,?,?)";
			
			PreparedStatement pstmt = null;
			
			try {
				pstmt = manager.getConn().prepareStatement(sql);
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPasswd());
				pstmt.setString(3, dto.getName());
				pstmt.setString(4, dto.getNick());
				pstmt.setInt(5, dto.getGradeNo());
				
				count = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return count;
		}

		public MemberDTO selectMember(String id) {
			String sql = "select * from board_member where id like ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberDTO dto = null;
			try {
				pstmt = manager.getConn().prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					String passwd = rs.getString(2);
					String name = rs.getString(3);
					String nick = rs.getString(4);
					int gradeNo = rs.getInt(5);
					dto = new MemberDTO(id, passwd, name, nick, gradeNo);				
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dto;
		}

		public int updateMember(MemberDTO dto) {
			int count = 0;
			
			String sql = "update board_member set passwd = ?, name = ?, nick = ?, grade_no = ? where id like ?";
			PreparedStatement pstmt = null;
			
			try {
				pstmt = manager.getConn().prepareStatement(sql);
				pstmt.setString(1, dto.getPasswd());
				pstmt.setString(2, dto.getName());
				pstmt.setString(3, dto.getNick());
				pstmt.setInt(4, dto.getGradeNo());
				pstmt.setString(5, dto.getId());
				
				count = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return count;
		}

		public int deleteMember(String id) {
			int count = 0;
			String sql = "delete board_member where id like ?";
			PreparedStatement pstmt = null;
			
			try {
				pstmt = manager.getConn().prepareStatement(sql);
				pstmt.setString(1, id);
				count = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return count;
		}

		public ArrayList<MemberDTO> searchMember(String kind, String search) {
			//전체 회원정보를 DB에서 조회해서 ArrayList로 리턴
			//1. SQL문 작성
			String sql = "select * from board_member where "+kind+" like '%' || ? || '%'";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			try {
				//2. PreparedStatement 생성
				pstmt = manager.getConn().prepareStatement(sql);
				pstmt.setString(1, search);
				//3. pstmt 실행
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String id = rs.getString("id");
					String passwd = rs.getString(2);
					String name = rs.getString(3);
					String nick = rs.getString(4);
					int gradeNo = rs.getInt(5);
					list.add(new MemberDTO(id, passwd, name, nick, gradeNo));				
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}
		
		public ArrayList<HashMap<Integer, String>> selectAllGrade(){
			ArrayList<HashMap<Integer, String>> list = new ArrayList<HashMap<Integer,String>>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from board_member_grade";
				pstmt = manager.getConn().prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					HashMap<Integer, String> map = new HashMap<Integer, String>();
					map.put(rs.getInt(1), rs.getString(2));
					list.add(map);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public int deleteGrade(int gradeNo) {
			int count = 0;
			String sql = "delete from board_member_grade where grade_no = ?";
			PreparedStatement pstmt = null;

			try {
				pstmt = manager.getConn().prepareStatement(sql);
				pstmt.setInt(1, gradeNo);
				count = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return count;
		}

		public ArrayList<HashMap<String, Object>> selectGrade(String search) {
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from board_member_grade "
						+ "where grade_name like '%' || ? || '%'";
				pstmt = manager.getConn().prepareStatement(sql);
				pstmt.setString(1, search);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("grade_no", rs.getInt(1));
					map.put("grade_name", rs.getString(2));
					list.add(map);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}

		
		public int insertGrade(int gradeNo, String gradeName) {
			int count = 0;
			String sql = "insert into board_member_grade values(?,?)";
			PreparedStatement pstmt = null;

			try {
				pstmt = manager.getConn().prepareStatement(sql);
				pstmt.setInt(1, gradeNo);
				pstmt.setString(2, gradeName);
				count = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return count;
		}

		public int updateGrade(int gradeNo, String gradeName) {
			int count = 0;
			String sql = "update board_member_grade set grade_name = ? where grade_no =?";
			PreparedStatement pstmt = null;

			try {
				pstmt = manager.getConn().prepareStatement(sql);
				pstmt.setString(1, gradeName);
				pstmt.setInt(2, gradeNo);
				count = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return count;
		}

	
	}
