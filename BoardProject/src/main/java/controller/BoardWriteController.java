package controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dto.BoardDTO;
import dto.FileDTO;
import dto.MemberDTO;
import service.BoardService;
import service.MemberService;
import view.ModelAndView;

public class BoardWriteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1. 업로드할 경로, DiskFileFactory 셋팅
		 * 2. 사용자가 보낸 내용을 받아서, BoardDTO와 FileDTO 셋팅
		 * 3. 파일 쓰기 먼저 수행
		 * 4. DB로 게시글, 파일목록을 테이블에 등록
		*/

		//업로드할 기본 폴더 설정 - 서버가 재배포되어도 파일이 계속 남아있는다.
		File userRoot = new File("c:\\fileupload\\");
		//업로드할 기본 폴더가 없으면 경로까지의 폴더를 생성
		if(!userRoot.exists()) userRoot.mkdir();
		 
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(userRoot);//업로드 될 폴더
		factory.setSizeThreshold(1024*1024);//버퍼 메모리 1메가
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			int fno = 0;
			//DB에 저장할 파일 목록
			ArrayList<FileDTO> fList = new ArrayList<FileDTO>();
			//폼에서 보낸 모든 내용을 받음
			List<FileItem> list = upload.parseRequest(request); 
			//DB에 저장할 게시글 정보를 담을 객체
			BoardDTO board = new BoardDTO();
			//작성자 저장
			board.setWriter(((MemberDTO)request.getSession().getAttribute("dto")).getId());
			
			for (FileItem item : list) {
				//사용자가 입력한 내용인지 확인 = isFormField() boolean타입
				if(item.isFormField()) {
					System.out.println(item.getFieldName() + " = " + item.getString("utf-8"));
					request.setAttribute(item.getFieldName(),item.getString("utf-8"));
					//글제목, 내용을 dto에 셋팅
					switch(item.getFieldName()) {
					case "title":
						board.setTitle(item.getString("utf-8"));
						break;
					case "content":
						board.setContent(item.getString("utf-8"));
						break;
					}
				}else {
					if(item.getSize() == 0) continue; //전송된 파일이 없을때
					
					//파일 업로드 처리
					System.out.println("매개변수 명 : " + item.getFieldName());
					System.out.println("파일 명 : " + item.getName());
					System.out.println("파일크기 : " + item.getSize());
					System.out.println("파일타입 : " + item.getContentType());

					//저장할 파일 경로 및 파일명 셋팅
					File uploadFile = new File(userRoot + "\\" + item.getName());
					item.write(uploadFile); //파일 업로드 처리
					fList.add(new FileDTO(uploadFile,0,++fno));
				}
			}
			//파일 업로드가 완료된 후에 게시글 번호를 받아서 게시글 DB 등록
			int bno = BoardService.getInstance().selectBoardBno();
			board.setBno(bno);
			BoardService.getInstance().insertBoard(board);
			//파일 목록을 DB에 등록
			for(int i = 0; i < fList.size(); i++) {
				fList.get(i).setBno(bno);
				BoardService.getInstance().insertFile(fList.get(i));
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(request.getContextPath()+"/main.do", true);
	}

}
