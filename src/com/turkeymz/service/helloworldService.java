package com.turkeymz.service;

/**
 * 
* @ClassName: helloworldService 
* @Description: Service逻辑处理层
* @author cmxu
* @date 2017年9月21日 下午3:37:41 
*
 */
public class helloworldService {

	public String helloworld(String jsonData) {
		System.out.println("【接收报文】"+jsonData);
		return "hello world!";
	}
}
