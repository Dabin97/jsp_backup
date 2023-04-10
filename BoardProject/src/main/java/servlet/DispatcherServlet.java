package servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.HandlerMapping;
import view.ModelAndView;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do") //와일드카드 *을 쓰면 모든 요청을 받는다. '.do'로 끝나면 다 이쪽을 보내겠다는 뜻,.do로만 끝나는 애들만 받겠다, *만 쓰면 모든 경로를 받겠다.
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] arr = request.getRequestURI().split("/"); //url 요청을 슬러쉬(/)로 쪼개서 관리하겠다. 쪼갰으니까 배열로 받아 관리한다.
//		System.out.println(Arrays.toString(arr)); 확인용
		Controller controller = HandlerMapping.getInstance().create(arr[arr.length-1]); //배열의 마지막꺼를 가져오겠다.
		
		ModelAndView view = null;
		if(controller != null)
			view = controller.execute(request, response); //일 시키기(컨트롤러가 있을때만 작동)
		
		if(view == null) return; //이동할 페이지가 없을때 리턴(ajax쓸땐 값을 보내줘서 적용시키는 거라서 페이지 이동할게 없으니 view가 null이다. 이런 경우 대비용)
		
		if(view.isRedirect())
			response.sendRedirect(view.getPath()); //redirect가 트루이면 이동할 경로를 뽑기
		else
			request.getRequestDispatcher(view.getPath()).forward(request, response); //아니라면 포워드 방식으로 보내기
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
