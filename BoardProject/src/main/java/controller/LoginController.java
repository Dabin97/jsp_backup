package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberDTO;
import service.MemberService;
import view.ModelAndView;

public class LoginController implements Controller {

	//mybatis 서블릿에서 하던일을 전부 컨트롤러에서 하게 된다.
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDTO dto = MemberService.getInstance().login(id, passwd);
		
		if(dto != null ) {
			HttpSession session = request.getSession();
			session.setAttribute("dto", dto);
			return new ModelAndView(request.getContextPath()+"/main.do", true);
		//그냥 "/main.do"만 보내면 슬러시앞에 개인 아이피 주소가 붙으므로 앞에 이걸 붙여야한다.
		}
		return new ModelAndView("/index.jsp", false); //로그인 실패시 로그인창으로 이동(forword방식은 페이지 이동시 경로가 남아있기떄문에 request.getContextPath()를 붙이지 않아도 된다.)
	}
	
}
	
	//ajax로 데이터를 보내주는것만 한다면 이동할 페이지가 없기때문에 return null;을 보내주면된다. 
	//만약 이동할 페이지가 있으면 ModelAndView로 만들어서 return한다. (ModelAndView = 경로만)
	
