package com.inspur.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inspur.inf.ClxxInf;
import com.inspur.inf.HjhcxxInf;
import com.inspur.utils.JsonDateFormater;

@RestController
public class ClxxController {

	@Autowired
	private ClxxInf ClxxInf;
	//车辆驾驶人信息查询
	@RequestMapping(value="/cljsrxx.do",method=RequestMethod.GET,produces="text/json;charset=UTF-8")
	public Object getjsrxxController(@RequestParam("SFZH") String SFZH)
	{
		synchronized (this)
		{
			 String obj = JsonDateFormater.JsonDateListToString(ClxxInf.getJsrxxInf(SFZH));
			 System.out.println("驾驶人信息查询:"+obj);
			 return obj;
		}
	}
	//车辆信息查询
	@RequestMapping(value="/clxx.do",method=RequestMethod.GET,produces="text/json;charset=UTF-8")
	public Object getClxxController(@RequestParam("ZJH") String ZJH)
	{
		synchronized (this)
		{
			 String obj = JsonDateFormater.JsonDateListToString(ClxxInf.getClxxInf(ZJH));
			 System.out.println("驾驶人信息查询:"+obj);
			 return obj;
		}
	}
    
	//机动车信息查询通过身份证号+车牌号码
	@RequestMapping(value="/clxxx.do",method=RequestMethod.GET,produces="text/json;charset=UTF-8")
	public Object getClxxxController(String SFZH, String HPHM)
	{
		synchronized (this)
		{
			 String obj = JsonDateFormater.JsonDateListToString(ClxxInf.getClxxInf(SFZH, HPHM));
			 System.out.println("车辆信息查询:"+obj);
			 return obj;
		}
	}

	@RequestMapping(value="/wzxxcx.do",method=RequestMethod.GET,produces="text/json;charset=UTF-8")
	public Object getWzxxCxController(String JSZH,String HPHM,Integer PAGENOW)
	{
		synchronized (this)
		{
			 Map<String, Object> wzxx = ClxxInf.getWzxxInf(JSZH, HPHM, PAGENOW);
			 System.out.println("违章信息查询"+wzxx);
			 return JsonDateFormater.JsonDateListToString(wzxx);
		}
	}
}
