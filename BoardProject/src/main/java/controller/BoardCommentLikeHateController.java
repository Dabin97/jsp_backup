package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dto.MemberDTO;
import service.BoardService;
import view.ModelAndView;

public class BoardCommentLikeHateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int cno = Integer.parseInt(request.getParameter("cno"));
		String mode = request.getParameter("mode"); //mode = class_name
		String id = ((MemberDTO)request.getSession().getAttribute("dto")).getId();
		
		boolean result = BoardService.getInstance().commentLikeHate(cno, mode, id);
		String msg = null;
		if(mode.equals("btn_comment_like")) {
			msg = result ? "해당 댓글에 좋아요 하였습니다." : "해당 댓글에 좋아요를 해제했습니다."; 
		}else {
			msg = result ? "해당 댓글에 싫어요 하였습니다." : "해당 댓글에 싫어요를 해제했습니다.";
		}
		JSONObject json = new JSONObject();
		json.put("msg",msg);
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(json.toString());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}

