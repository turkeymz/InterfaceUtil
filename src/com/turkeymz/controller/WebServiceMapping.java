package com.turkeymz.controller;

import java.util.HashMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.turkeymz.cache.ServiceMethodCache;
import com.turkeymz.cache.ServiceMethodEntity;

import net.sf.json.JSONObject;

public class WebServiceMapping {
    private HashMap<String, ServiceMethodEntity> map = new HashMap<>();

	public String mapping(String jsonStr) {
		String tranType = "",result = "";
		JSONObject json_result = new JSONObject();
		try {
			//获取缓存
			map = ServiceMethodCache.map;
			//解析报文
			JSONObject json = JSONObject.fromObject(jsonStr);
			//获取共有报文
			tranType = json.getString("type");//请求类型(辨认走哪个逻辑)
			//获取私有报文
			JSONObject data_json = json.getJSONObject("data");
			//读取缓存
			ServiceMethodEntity sm = map.get(tranType);
		    String className = sm.getClassName();
		    String methodName = sm.getMethodName();
		    
			//反射进java
		    Class clazz = Class.forName(className);
		    Object obj = clazz.newInstance();
			Method m = obj.getClass().getDeclaredMethod(methodName,String.class);
			result = (String) m.invoke(obj,data_json.toString());
			//打包反悔
			json_result.put("retmsg", result);
			json_result.put("retcode", "0");
			return json_result.toString();
		} catch (InvocationTargetException  e) {
			e.printStackTrace();
			Throwable t = e.getTargetException();
			json_result.put("retcode", "-1");// 错误代码
			json_result.put("retmsg", t.getMessage());// 错误信息
			return json_result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			json_result.put("retcode", "-1");// 错误代码
			json_result.put("retmsg", e.getMessage());// 错误信息
			return json_result.toString();
		}
	}
}
