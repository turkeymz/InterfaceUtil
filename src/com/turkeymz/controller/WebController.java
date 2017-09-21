package com.turkeymz.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
* @ClassName: WebController 
* @Description: Controller接收层
* @author cmxu
* @date 2017年9月21日 下午3:37:21 
*
 */
@WebServlet("/WebController")
public class WebController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sTransMessage = request.getParameter("TransMessage");//接受参数
		WebServiceMapping wsm = new WebServiceMapping();
		String reuslt = wsm.mapping(sTransMessage);//具体逻辑映射
		ServletOutputStream outStream = response.getOutputStream();//响应
		outStream.println(reuslt);
		outStream.flush();
		outStream.close();
	}

}
