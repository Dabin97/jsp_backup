package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import service.BoardService;
import view.ModelAndView;

public class BoardUpdateViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardDTO dto = BoardService.getInstance().selectBoard(bno);
		ModelAndView view = null;
		if (dto != null) {
			view = new ModelAndView("board_update_view.jsp", false);
			request.setAttribute("board", dto);
		} else {
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().append("<script>");
				response.getWriter().append("alert('해당 게시글이 없습니다.');");
				response.getWriter().append("location.href='"+request.getContextPath()+"/main.do';");
				response.getWriter().append("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return view;
	}

}
