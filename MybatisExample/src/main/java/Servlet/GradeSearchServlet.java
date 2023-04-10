package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import service.GradeService;


/**
 * Servlet implementation class GradeSearchServlet
 */
@WebServlet("/grade/search")
public class GradeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("val");
		response.setCharacterEncoding("utf-8");
		//회원 등급 목록 조회
		List<HashMap<String, Object>> list = 
				GradeService.getInstance().selectGrade(search);
		//json으로 셋팅해서 클라이언트에게 전송
		JSONArray arr = new JSONArray(list);
		System.out.println(arr.toString()); 
		//syso결과를 출력해보니 GRADE_NO, GRADE_NAME 이렇게 대문자로 나오므로, grade_manage.jsp에서 소문자->대문자로 변경한다.
		response.getWriter().println(arr.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
