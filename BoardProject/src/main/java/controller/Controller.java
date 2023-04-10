package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import view.ModelAndView;

public interface Controller {
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response); //doGet에 있던거 여기에 적용
	
}
