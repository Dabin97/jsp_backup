package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/upload.do")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//업로드할 기본 폴더 설정 - 서버가 재배포되어도 파일이 계속 남아있는다.
		File userRoot = new File("c:\\fileupload\\");
		//업로드할 기본 폴더가 없으면 경로까지의 폴더를 생성
		if(!userRoot.exists()) userRoot.mkdir();
		 
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(userRoot);//업로드 될 폴더
		factory.setSizeThreshold(1024*1024);//버퍼 메모리 1메가
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			ArrayList<FileDTO> fList = new ArrayList<FileDTO>();
			//폼에서 보낸 모든 내용을 받음
			List<FileItem> list = upload.parseRequest(request); //form에서 받은 것을 list에 넣기
			for (FileItem item : list) {
				//사용자가 입력한 내용인지 확인 = isFormField() boolean타입
				if(item.isFormField()) {
					System.out.println(item.getFieldName() + " = " + item.getString("utf-8"));
					request.setAttribute(item.getFieldName(),item.getString("utf-8"));
				}else {
					if(item.getSize() == 0) continue; //전송된 파일이 없을때
					
					//파일 업로드 처리
					System.out.println("매개변수 명 : " + item.getFieldName());
					System.out.println("파일 명 : " + item.getName());
					System.out.println("파일크기 : " + item.getSize());
					System.out.println("파일타입 : " + item.getContentType()); //미리보기가 가능한 형태인지 체크하기 위해서 넣은것(ex.이미지)

					//저장할 파일 경로 및 파일명 셋팅
					File uploadFile = new File(userRoot + "\\" + item.getName());
					item.write(uploadFile); //파일 업로드 처리
					fList.add(new FileDTO(uploadFile.getAbsolutePath(), item.getName(), item.getContentType().split("/")[0]));
				}
			}
			request.setAttribute("fList", fList);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("file_result.jsp").forward(request, response);
	}
	//서버에 바로 파일 업로드하면 저장할때마다 예전 파일이 날아감. 따로 저장해둬야함

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
