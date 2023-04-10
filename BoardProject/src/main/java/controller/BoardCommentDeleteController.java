package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import view.ModelAndView;

public class BoardCommentDeleteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		int cno = Integer.parseInt(request.getParameter("cno"));
		
		BoardService.getInstance().deleteBoardComment(cno);
		
		return new ModelAndView(request.getContextPath()+"/boardView.do?bno="+bno, true);
	}

}

