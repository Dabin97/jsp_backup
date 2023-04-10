package page;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/session_test")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 객체 가져옴
		HttpSession session = request.getSession();
		//이름
		String name = request.getParameter("name");
		//나이
		int age = Integer.parseInt(request.getParameter("age"));
		//세션에 데이터 저장
		session.setAttribute("name", name);
		session.setAttribute("age", age);
		//세션 만료 시간 설정
		session.setMaxInactiveInterval(60);
		
		//05_session_result.jsp로 이동
		//forward 방식으로 이동
		//request.getRequestDispatcher("05_session_result.jsp").forward(request, response);
		//redirect 방식으로 이동
		response.sendRedirect("05_session_result.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//한글 깨짐 방지 처리
		doGet(request, response);
	}

}
