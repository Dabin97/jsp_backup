package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import service.GradeService;
import service.MemberService;

/**
 * Servlet implementation class GradeDeleteServlet
 */
@WebServlet("/grade/delete/*")//경로 설정, grade --> delete로
public class GradeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(request.getRequestURI()); //사용자가 호출한 url을 보는 것
//		System.out.println(request.getServletPath());
//		System.out.println(request.getContextPath());
//		response.getWriter().append("Served at: ").append(request.getContextPath());//getContextPath() 프로젝트명에 해당하는 url을 뽑아줌
		
		String requestURI = request.getRequestURI();
		//받아온 데이터 읽어오는 부분
		int gradeNo = Integer.parseInt(requestURI.substring(requestURI.lastIndexOf('/')+1));
		System.out.println(requestURI.substring(requestURI.lastIndexOf('/')+1));
		//실제 데이터 처리 시작하는 부분
		int result = GradeService.getInstance().deleteGrade(gradeNo);
		JSONObject json = new JSONObject();
		json.put("code", result);
		if(result == 1)
			json.put("message", "회원등급 삭제 완료");
		else
			json.put("message", "회원등급 삭제 실패");
		
		//결과 전송
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(json.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
