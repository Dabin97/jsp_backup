package page;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForWardServlet
 */
@WebServlet("/forward_move") //경로 이름 설정을 해줘야 적용됨
public class ForWardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForWardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//forward 방식으로 페이지를 이동하면
		//사용자의 요청을 계속 이어서 받겠다라는 의미
		//request 객체를 계속 유지를 함
		//request에 값을 저장하는 방법
		request.setAttribute("msg", "안녕하세요"); //속성을 지정
		//페이지 이동
		request.getRequestDispatcher("02_forward_result.jsp").forward(request, response);
		//RequestDispatcher는 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행하거나
		//특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스입니다.

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
