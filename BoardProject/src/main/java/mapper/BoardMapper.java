package mapper;

import java.util.HashMap;
import java.util.List;

import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;

import config.DBManager;
import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.FileDTO;

public class BoardMapper {
	private static BoardMapper instance = new BoardMapper();
	private SqlSession session;
	private BoardMapper() {
		session = DBManager.getInstance().getSession();
		
	}

	public static BoardMapper getInstance() {
		if(instance==null)
			instance = new BoardMapper();
		return instance;
	}

	public int insertBoard(BoardDTO dto) {
		return session.insert("insertBoard", dto);
	}

	public List<BoardDTO> selectAllBoard() {
		return session.selectList("selectAllBoard");
	}

	public BoardDTO selectBoard(int bno) {
		return session.selectOne("selectBoard", bno);
	}

	public void updateBoardCount(int bno) {
		session.update("updateBoardCount",bno);
	}

	public void deleteBoard(int bno) {
		session.delete("deleteBoard", bno);
	}

	public void updateBoard(BoardDTO dto) {
		session.update("updateBoard", dto);
	}

	public int insertBoardLike(HashMap<String, Object> map) {
		return session.insert("insertBoardLike",map);
	}

	public int selectBoardLike(int bno) {
		return session.selectOne("selectBoardLike", bno);
	}

	public int deleteBoardLike(HashMap<String, Object> map) {
		return session.delete("deleteBoardLike",map);
	}

	public int selectBoardHate(int bno) {
		return session.selectOne("selectBoardHate", bno);
	}

	public int insertBoardHate(HashMap<String, Object> map) {
		return session.insert("insertBoardHate",map);
	}

	public int deleteBoardHate(HashMap<String, Object> map) {
		return session.delete("deleteBoardHate",map);
	}

	public void insertBoardComment(BoardCommentDTO dto) {
		session.insert("insertBoardComment", dto);
	}

	public List<BoardCommentDTO> selectCommentList(int bno) {
		return session.selectList("selectCommentList", bno);
	}

	public void deleteBoardComment(int cno) {
		session.delete("deleteBoardComment", cno);
	}

	public void insertCommentLikeHate(HashMap<String, Object> map) {
		session.insert("insertCommentLikeHate", map);
	}
	public void deleteCommentLikeHate(HashMap<String, Object> map) {
		session.delete("deleteCommentLikeHate", map);
	}

	public int selectBoardBno() {
		return session.selectOne("selectBoardBno");
	}

	public int insertFile(FileDTO fileDTO) {
		return session.insert("insertFile", fileDTO);
	}

	public List<FileDTO> selectFileList(int bno) {
		return session.selectList("selectFileList",bno);
	}

	public FileDTO selectFile(HashMap<String, Object> map) {
		return session.selectOne("selectFile",map);
	}
	public int selectBoardCount() {
		return session.selectOne("selectBoardCount");
	}
	public List<BoardDTO> selectBoardList(HashMap<String, Object> map) {
		return session.selectList("selectBoardList",map);
	}
	

}
