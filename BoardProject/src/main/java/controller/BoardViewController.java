package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.FileDTO;
import service.BoardService;
import view.ModelAndView;

public class BoardViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView view = null;
			
			int bno = Integer.parseInt(request.getParameter("bno"));
			//로그아웃시 데이터 초기화 = 세션, 새로고침시 조회수 안올라가게, 중복X = set
			HttpSession session = request.getSession();
			HashSet<Integer> set = (HashSet<Integer>) session.getAttribute("bno_log");
			if(set == null) { //접속기록이 없을때
				set = new HashSet<Integer>();
				session.setAttribute("bno_log", set); //접속기록 추가
			}
			System.out.println(set.toString());//증가됐는지 아닌지 확인용
			
			if(set.add(bno)) //set.add(boolean값으로 나온다)  bno가 true일때 조회수 +1 증가
				BoardService.getInstance().updateBoardCount(bno); //수정하려고 게시글번호를 눌렀을때 조회수+1 증가
			
			BoardDTO dto = BoardService.getInstance().selectBoard(bno);
			List<BoardCommentDTO> list = BoardService.getInstance().selectCommentList(bno); //댓글목록 list를 추가
			//파일 목록
			List<FileDTO> fList = BoardService.getInstance().selectFileList(bno);
			if(dto != null) {
				view = new ModelAndView("board_view.jsp", false);
				request.setAttribute("board", dto); //본문
				request.setAttribute("list", list); //댓글
				request.setAttribute("fList", fList); //댓글
				
			}else {
				response.setContentType("text/html;charset=utf-8");
				try {
					response.getWriter().append("<script>");
					response.getWriter().append("alert('해당 게시글이 없습니다.');");
					response.getWriter().append("history.back();");
					response.getWriter().append("</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return view;
		}


}
