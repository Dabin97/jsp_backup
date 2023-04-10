package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.MemberService;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 보낸 회원정보를 받고
				String id = request.getParameter("id");
				String passwd = request.getParameter("passwd");
				String name = request.getParameter("name");
				String nick = request.getParameter("nick");
				int gradeNo = Integer.parseInt(request.getParameter("grade"));
				
				//2. MemberDTO 객체 생성
				MemberDTO dto = new MemberDTO(id, passwd, name, nick, gradeNo);
				
				//3. Service 클래스에 dto 전달
				int count = MemberService.getInstance().updateMember(dto);
				response.setContentType("text/html; charset=utf-8");
				if(count == 0) {
					//수정 실패
					response.getWriter().append("<script>");
					response.getWriter().append("alert('회원정보 수정 실패');");
					response.getWriter().append("history.back();");
					response.getWriter().append("</script>");
				}else {
					//수정 성공
					response.getWriter().append("<script>");
					response.getWriter().append("alert('회원정보 수정 성공');");
					response.getWriter().append("location.href = 'main';");
					response.getWriter().append("</script>");
				}
				response.sendRedirect("main");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
