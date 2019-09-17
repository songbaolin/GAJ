package com.inspur.utils;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonDateFormater {

	public static String JsonDateToString(Object obj)
	{
		JsonConfig jsonconfig=new JsonConfig();
		jsonconfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
		jsonconfig.registerJsonValueProcessor(java.sql.Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		jsonconfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss.SSS"));
        JSONObject json =JSONObject.fromObject(obj,jsonconfig);
        String s = json.toString();
        return s;
	}
	
	public static String JsonDateListToString(Object obj)
	{
		JsonConfig jsonconfig=new JsonConfig();
		jsonconfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
		jsonconfig.registerJsonValueProcessor(java.sql.Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		jsonconfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss.SSS"));
		JSONArray jsonarray = JSONArray.fromObject(obj,jsonconfig);
        String s = jsonarray.toString();
        return s;
	}
}
