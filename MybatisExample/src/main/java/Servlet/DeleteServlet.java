package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import service.MemberService;

/**
 * Servlet implementation class DelteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//json으로 객체를 하나 작성 클라이언트에게 전송
		String id = request.getParameter("id");
		int count = MemberService.getInstance().delelteMember(id);
		
		//클라이언트에게 전송할 데이터는 json으로 생성
		JSONObject result = new JSONObject();
		if(count == 1)
			result.put("message", "데이터 삭제 성공");
		else
			result.put("message", "데이터 삭제 실패");
		result.put("count", count);
		
		//Writer 이용해서 클라이언트에게 json내용을 문자열로 전송, 페이지 이동이 아니라 문자열만 보내므로 writer를 이용한다.
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(result.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
