package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.GradeService;
import service.MemberService;

/**
 * Servlet implementation class GradeServlet
 */
@WebServlet("/grade")
public class GradeMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> list = 
				GradeService.getInstance().selectAllGrade();
		/*
		for(Map<String, Object> map : list) {
			Iterator<String> it = map.keySet().iterator();
			while(it.hasNext()) {
				String key = (String)it.next();
				System.out.println(key + " : " + map.get(key));
			}
				System.out.println("----------------------");
		}
			System.out.println();
		*/
		
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("grade_manage.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
