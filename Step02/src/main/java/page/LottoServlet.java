package page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LottoServlet
 */
@WebServlet("/lotto")
public class LottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LottoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<HashSet<Integer>>list = new ArrayList<HashSet<Integer>>();
		Random r = new Random();
		int num = Integer.parseInt(request.getParameter("num")); 

		for (int i = 0; i < num; i++) {
			HashSet<Integer> set = new HashSet<Integer>();
			
			while(set.size() < 6) {
				set.add(r.nextInt(45)+1);
			}
			
			list.add(set);
		}
		
		//lotto에 list값 넣어주기
		request.setAttribute("lotto", list);
		
		//이동 forward 형식
		request.getRequestDispatcher("lotto_result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
