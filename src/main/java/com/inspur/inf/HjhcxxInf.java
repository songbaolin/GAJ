package com.inspur.inf;

import java.util.List;
import java.util.Map;

public interface HjhcxxInf {

	//人口户籍信息
	public List<Map<String, Object>> getSfZGetHjxx(String SFZH);
	//人口户籍信息比对
	public int getSfZAndXmGetHjxx(String SFZH,String XM);
	//人员照片信息
	public List<Map<String, Object>> getRyzpxx(String SFZH);
	//查询家庭成员信息通过户号ID查询
	public List<Map<String, Object>> getRyHid(String HHID);
	
	//身份证号查询违法信息
	public Map<String, Object> getWfryxx(String SFZH);
}
