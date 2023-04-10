package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FileDTO;
import service.BoardService;
import view.ModelAndView;

public class FileDownController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		//bno, fno로 다운로드 받아야될 파일 정보 하나 조회
				int bno = Integer.parseInt(request.getParameter("bno"));
				int fno = Integer.parseInt(request.getParameter("fno"));
				FileDTO dto = BoardService.getInstance().selectFile(bno, fno);
				String path = dto.getPath();
				File file = new File(path);
				String fileName = dto.getFileName();
				
				try {
					fileName = URLEncoder.encode(fileName,"utf-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				response.setHeader("Content-Disposition", "attachement;fileName="+fileName);
				response.setHeader("Content-Transfer-Encoding", "binary");
				response.setContentLength((int)file.length());
				try(FileInputStream fis = new FileInputStream(file);
					BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());) {
					
					byte[] buffer = new byte[1024*1024];
					
					while(true) {
						int size = fis.read(buffer);
						if(size == -1) break;
						bos.write(buffer,0,size);
						bos.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}

		}

