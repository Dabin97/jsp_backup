package page;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//세션 객체 가져옴
				HttpSession session = request.getSession();
				//아이디
				String id = request.getParameter("id");
				//비밀번호
				String passwd = request.getParameter("passwd");
	
				if(id.equals("admin") && passwd.equals("123456")) {
					session.setAttribute("id", id);
					session.setAttribute("msg", "로그인 성공");
				}else {
					session.setAttribute("msg", "로그인 실패");
				}

				//세션 만료 시간 설정
				session.setMaxInactiveInterval(60);
				
				//이동 forward 형식
				request.getRequestDispatcher("login_result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //한글깨짐방지
		doGet(request, response);
	}

}
