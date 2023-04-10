package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dto.MemberDTO;
import service.BoardService;
import view.ModelAndView;

public class BoardContentHateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		int bno = Integer.parseInt(request.getParameter("bno"));
		MemberDTO dto = (MemberDTO) request.getSession().getAttribute("dto");
		try {
			if(dto == null) throw new Exception("1001");//로그인 안한 경우
			
			//좋아요 처리가 정상적으로 된 경우
			BoardService.getInstance().boardHate(bno, dto.getId());
			json.put("code",true);
			json.put("msg", "해당 게시글에 싫어요를 하셨습니다.");
			json.put("bhate", BoardService.getInstance().selectBoardHate(bno));
		}catch (Exception e) {
			json.put("code",false);
			if(e.getMessage().equals("1001"))
				json.put("msg", "로그인 하셔야 이용할 수 있습니다.");
			else {
				json.put("msg", "해당 게시글에 싫어요를 해제하셨습니다.");
				BoardService.getInstance().deleteBoardHate(bno, dto.getId());
			}
			json.put("bhate", BoardService.getInstance().selectBoardHate(bno));
			
		}finally {
			try {
				response.setCharacterEncoding("utf-8");
				response.getWriter().println(json.toString());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
		}

	}


