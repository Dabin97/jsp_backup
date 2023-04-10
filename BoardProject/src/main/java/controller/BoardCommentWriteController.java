package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.MemberDTO;
import service.BoardService;
import view.ModelAndView;

public class BoardCommentWriteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String content = request.getParameter("comment");
		String writer = ((MemberDTO)request.getSession().getAttribute("dto")).getId();
		
		BoardService.getInstance().insertBoardComment(
				new BoardCommentDTO(bno, writer, content));
		
		return new ModelAndView(request.getContextPath()+"/boardView.do?bno="+bno, true);

	}

}
