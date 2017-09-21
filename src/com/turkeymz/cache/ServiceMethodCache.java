package com.turkeymz.cache;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import org.jdom.Element;

public class ServiceMethodCache {
	private static Log log = LogFactory.getLog(ServiceMethodCache.class);
	public static HashMap<String, ServiceMethodEntity> map = new HashMap<>();
	
	public HashMap<String, ServiceMethodEntity> getMap() {
		return map;
	}

	public void setMap(HashMap<String, ServiceMethodEntity> map) {
		this.map = map;
	}

	public boolean load()  {
		log.info("[CACHE] ServiceMethodCache bulid Begin .................");
        SAXBuilder b = new SAXBuilder();
        Document xDocument = null;
        ServiceMethodEntity m = null;
        //String path = this.getClass().getClassLoader().getResource("serviceMethod.xml").getPath();
        String path = ServiceMethodCache.class.getClassLoader().getResource("/").getPath();
        path = path.substring(0, path.lastIndexOf("classes")) + "etc/serviceMethod.xml";
        log.info("load serviceMethod.xml path={"+path+"}");

        try {
            xDocument = b.build(path);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        Element xService = xDocument.getRootElement();
        for (Iterator itr = xService.getChildren("method").iterator(); itr.hasNext();){
            Element xMethod = (Element) itr.next();
            m = new ServiceMethodEntity();
            m.setClassName(xMethod.getAttributeValue("class"));
            m.setMethodName(xMethod.getAttributeValue("method"));
            m.setType(xMethod.getAttributeValue("type"));
            map.put(xMethod.getAttributeValue("type"), m);
        }
		log.info("[CACHE] ServiceMethodCache bulid End .................");
		return true;
	}
	
	public static void main(String[] args) {
		ServiceMethodCache s = new ServiceMethodCache();
		log.info(s.getClass().getClassLoader().getResource("ServiceMethod.xml").getPath()+"+++++++++++");
        String path = s.getClass().getClassLoader().getResource("ServiceMethod.xml").getPath();
       System.out.println(path);
	}

}
