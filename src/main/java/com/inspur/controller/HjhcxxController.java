package com.inspur.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inspur.inf.HjhcxxInf;
import com.inspur.utils.JsonDateFormater;

@RestController
public class HjhcxxController {

	@Autowired
	private HjhcxxInf hjhcxxInf;
	//人口户籍信息核查
	@RequestMapping(value="/gajsfzh.do",method=RequestMethod.GET,produces="text/json;charset=UTF-8")
	public Object getSfZGetHjxxController(@RequestParam("SFZH") String SFZH)
	{
		synchronized (this)
		{
			 String obj = JsonDateFormater.JsonDateListToString(hjhcxxInf.getSfZGetHjxx(SFZH));
			 System.out.println("人口户籍信息核查:"+obj);
			 return obj;
		}
		
	}
	
	//人口信息比对
	@RequestMapping(value="/gajsfzhxm.do",method=RequestMethod.GET)
	public Object getSfZAndXMGetHjxxController(@RequestParam("SFZH") String SFZH,String XM)
	{
		synchronized (this)
		{
			 List list=new ArrayList();
			 System.out.println("获取SFZH"+SFZH+"获取姓名:"+XM);
			 int count = hjhcxxInf.getSfZAndXmGetHjxx(SFZH, XM);
			 Map<String, Object> map=new HashMap<String, Object>();
			 map.put("count", count);
			 list.add(map);
			 return list;
		}
		
	}
	
	//人员照片信息
	@RequestMapping(value="/ryzpxx.do",method=RequestMethod.GET,produces="text/json;charset=UTF-8")
	public String getRyzpxxController(@RequestParam("SFZH") String SFZH)
	{
		synchronized (this)
		{
			String obj=JsonDateFormater.JsonDateListToString(hjhcxxInf.getRyzpxx(SFZH));
			System.out.println("人员照片信息:"+obj);
			return obj;
		}
		
	}
	
	//人员家庭成员信息核查
	@RequestMapping(value="/ryjtxx.do",method=RequestMethod.GET,produces="text/json;charset=UTF-8")
	public Object getRyJtxxController(@RequestParam("HHID") String HHID)
	{
		synchronized (this)
		{
			 String obj = JsonDateFormater.JsonDateListToString(hjhcxxInf.getRyHid(HHID));
			 System.out.println("人口家庭信息核查:"+obj);
			 return obj;
		}
	}
     
	//人员家庭成员信息核查
	@RequestMapping(value="/rywfxx.do",method=RequestMethod.GET,produces="text/json;charset=UTF-8")
	public Object getRywfxxController(@RequestParam("SFZH") String SFZH)
	{
		synchronized (this)
		{
			 String obj = JsonDateFormater.JsonDateListToString(hjhcxxInf.getWfryxx(SFZH));
			 System.out.println("人口违法信息:"+obj);
			 return obj;
		}
	}
	
	@RequestMapping(value="/test.do")
	public Map<String, Object> TestUser()
	{
		System.out.println("测试数据。。。。");
		Map<String, Object> map=new HashMap<String, Object>();
		long i=10L;
		map.put("res", i);
		map.put("key", "测试");
		return map;		
	}
}
