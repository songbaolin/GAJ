package com.inspur.inf;

import java.util.List;
import java.util.Map;

public interface ClxxInf {
   
	//机动车信息查询
	public List<Map<String, Object>> getJsrxxInf(String SFZH);
	//驾驶人信息查询
	public List<Map<String, Object>> getClxxInf(String ZJH);
	//机动车身份证+车牌号信息查询
	public List<Map<String, Object>> getClxxInf(String SFZH,String HPHM);
	//驾驶证号+好牌号码查询机动车违章信息
	public Map<String, Object> getWzxxInf(String JSZH,String HPHM,Integer pagenow);
   
	
	
}
