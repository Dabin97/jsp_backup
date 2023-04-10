package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import dto.MemberDTO;
import service.BoardService;
import service.GradeService;
import view.ModelAndView;

public class BoardDeleteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		int bno = Integer.parseInt(request.getParameter("bno")); //str말고 int로 받아오기
		MemberDTO member = (MemberDTO) request.getSession().getAttribute("dto");
		BoardDTO board = BoardService.getInstance().selectBoard(bno);
		
		if(member == null || board == null || !member.getId().equals(board.getWriter())) { //멤버나 게시글이 없거나, 작성자가 아니라면 삭제실패처리
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().append("<script>");
				response.getWriter().append("alert('게시글 삭제에 실패했습니다.');");
				response.getWriter().append("location.href='"+request.getContextPath()+"/main.do';");//history.back();대신 메인페이지로
				response.getWriter().append("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			BoardService.getInstance().deleteBoard(bno);
			view = new ModelAndView(request.getContextPath()+"/main.do", true); //삭제후 메인페이지로 이동
		}
		return view;
	}

}
