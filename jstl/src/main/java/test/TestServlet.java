package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberDTO;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test.do")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//MemberDTO 생성 후 Session에 저장
				MemberDTO dto = new MemberDTO("A0001", "123456", "홍길동", "도둑놈", 1);
				HttpSession session = request.getSession();
				session.setAttribute("dto", dto);
				session.setAttribute("msg", "session message");
				
				//ArrayList 생성 3건이상 리스트에 저장
				ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
				list.add(new MemberDTO("B0002", "1234", "AAAAA", "A1234", 1));
				list.add(new MemberDTO("B0003", "1234", "BBBBB", "B1234", 3));
				list.add(new MemberDTO("B0004", "1234", "CCCCC", "CD234", 4));
				list.add(new MemberDTO("B0005", "1234", "DDDDD", "D1234", 2));
				
				request.setAttribute("list", list);
				request.setAttribute("age", 20);
				
				request.getRequestDispatcher("jstl_el.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
