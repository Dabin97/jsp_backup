package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import service.BoardService;
import view.ModelAndView;
import vo.PaggingVO;

public class MainController implements Controller {
	/*
	 * 1. 페이지 번호를 읽어와야됨
	 * 2. 게시글 전체 개수 조회
	 * 3. 게시글을 한 페이지당 몇개씩 보여줄건지 결정한 후 전체 게시글 개수를 이용해서 PaggingVO를 생성 
	 * 4. 페이지 번호에 해당하는 보여줄 게시글 개수를 보내서 해당 페이지의 게시글 목록을 조회
	 */
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		//페이지번호 셋팅
		int pageNo = 1;
		if(request.getParameter("pageNo") != null)
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		//전체 게시글 개수
		int count = BoardService.getInstance().selectBoardCount();
		
		//PaggingVO 생성
		PaggingVO pagging = new PaggingVO(count, pageNo, 7);
		request.setAttribute("pagging", pagging);
		
		//게시판 글 목록을 읽어오는 작업
		List<BoardDTO> list = BoardService.getInstance()
				.selectBoardList(pageNo,pagging.getPageOfContentCount());
		request.setAttribute("list", list);
		
		view = new ModelAndView("main.jsp", false);
		return view;

	}

}
