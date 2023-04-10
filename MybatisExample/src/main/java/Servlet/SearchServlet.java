package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dto.MemberDTO;
import service.MemberService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자가 보낸 데이터 받음
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		//2. 조회
		List<MemberDTO> list = MemberService.getInstance().searchMember(kind,search);
		//3. JSON 생성
		JSONArray arr = new JSONArray(list);
		//4. json 전송
		response.setCharacterEncoding("utf-8"); //문자열만 전송이라 encoding으로 한거고, 자바스크립트를 보낼때는 setConctantType으로 보내야한다.
		response.getWriter().println(arr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
}
