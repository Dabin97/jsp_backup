package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.javassist.bytecode.ExceptionsAttribute;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.FileDTO;
import mapper.BoardMapper;

public class BoardService {
	private static BoardService instance = new BoardService();
	private BoardMapper mapper;
	private BoardService() {	
		mapper = BoardMapper.getInstance();
	}

	public static BoardService getInstance() {
		if(instance==null)
			instance = new BoardService();
		return instance;
	}

	public int insertBoard(BoardDTO dto) {
		return mapper.insertBoard(dto);	
	}

	public List<BoardDTO> selectAllBoard() {
		return mapper.selectAllBoard();
	}


	public BoardDTO selectBoard(int bno) {
		return mapper.selectBoard(bno);
	}

	public void updateBoardCount(int bno) {
		mapper.updateBoardCount(bno);
	}

	public void deleteBoard(int bno) {
		mapper.deleteBoard(bno);
	}

	public void updateBoard(BoardDTO dto) {
		mapper.updateBoard(dto);
	}

	public int boardLike(int bno, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno",bno);
		map.put("id",id);
		return mapper.insertBoardLike(map);
	}

	public int selectBoardLike(int bno) {
		return mapper.selectBoardLike(bno);
	}

	public int deleteBoardLike(int bno, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno",bno);
		map.put("id",id);
		return mapper.deleteBoardLike(map);
	}

	public int boardHate(int bno, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno",bno);
		map.put("id",id);
		return mapper.insertBoardHate(map);
	}
	
	public int selectBoardHate(int bno) {
		return mapper.selectBoardHate(bno);
	}

	public int deleteBoardHate(int bno, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno",bno);
		map.put("id",id);
		return mapper.deleteBoardHate(map);
		
	}

	public void insertBoardComment(BoardCommentDTO dto) {
		mapper.insertBoardComment(dto);
	}

	public List<BoardCommentDTO> selectCommentList(int bno) {
		return mapper.selectCommentList(bno);
	}

	public void deleteBoardComment(int cno) {
		mapper.deleteBoardComment(cno);
	}

	public boolean commentLikeHate(int cno, String mode, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("mode", mode);
		map.put("writer", id);
		boolean result = false;
		try {
			//댓글 좋아요/싫어요 설정
			mapper.insertCommentLikeHate(map);
			result = true;
		}catch (Exception e) {
			//댓글 좋아요/싫어요 해제
			mapper.deleteCommentLikeHate(map);
		}
		
		return result;
	}

	public int selectBoardBno() {
		return mapper.selectBoardBno();
	}

	public int insertFile(FileDTO fileDTO) {
		return mapper.insertFile(fileDTO);
		
	}

	public List<FileDTO> selectFileList(int bno) {
		return mapper.selectFileList(bno);
	}

	public FileDTO selectFile(int bno, int fno) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno",bno);
		map.put("fno", fno);
		return mapper.selectFile(map);
	}

	public int selectBoardCount() {
		return mapper.selectBoardCount();
	}

	public List<BoardDTO> selectBoardList(int pageNo, int pageOfContentCount) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo",pageNo);
		map.put("contentCount", pageOfContentCount);
		return mapper.selectBoardList(map);
	}
	


}
