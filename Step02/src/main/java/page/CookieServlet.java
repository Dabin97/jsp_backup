package page;

import java.io.IOException;
import java.security.KeyStore.Entry.Attribute;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet
 */
@WebServlet("/cookie_setting")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp에서 보낸 값 key, val
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		
		//쿠키 생성
		Cookie cookie = new Cookie(key, val);
		cookie.setMaxAge(60*60*24); //쿠키 만료시간 설정 seconds단위
		cookie.setPath("/");//그냥 루트폴더에
		
		response.addCookie(cookie);//쿠키 등록
		response.sendRedirect("cookie_result.jsp");
		
		//Parameter = String타입, Attribute = object타입
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
