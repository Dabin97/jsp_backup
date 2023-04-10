package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				//사용자가 폼을 이용해서 보낸 값을 읽어오는 부분
				//getParameter로 쿼리스트링으로 보낸 값을 읽음, name 속성 값으로 보낸 값을 읽어옴
				String id = request.getParameter("id");
				String passwd = request.getParameter("passwd");
				System.out.println(id + "," + passwd);
				
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append("<h2>로그인 결과</h2>");
				response.getWriter().append("아이디 : " + id + "<br>");
				response.getWriter().append("암호 : " + passwd + "<br>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
