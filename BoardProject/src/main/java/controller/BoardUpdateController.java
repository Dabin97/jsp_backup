package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import service.BoardService;
import view.ModelAndView;

public class BoardUpdateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
		dto.setTitle(title);
		dto.setContent(content);
		BoardService.getInstance().updateBoard(dto);
		
		return new ModelAndView(request.getContextPath()+"/boardView.do?bno="+bno, true);
	}

}
