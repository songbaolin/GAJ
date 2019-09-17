package com.inspur.infImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inspur.inf.ClxxInf;
import com.inspur.utils.JsonDateFormater;
import com.inspur.utils.LongToStringUntils;

@Service
public class ClxxServiceImp implements ClxxInf {

	//第一个数据源
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate = null;
	
	//第二个数据源
	@Autowired
	@Qualifier("jdbcTemplatex")
	private JdbcTemplate jdbcTemplatex = null;
	//驾驶人信息查询
	public List<Map<String, Object>> getJsrxxInf(String SFZH) {
		List<Map<String, Object>> list = new ArrayList();
		if (!StringUtils.isEmpty(SFZH)) {
			list = jdbcTemplate.queryForList("select * from JJ_DRIVINGLICENSE where SFZMHM=?", SFZH);
		}
		return LongToStringUntils.mapToString(list);
	}
	//机动车信息查询
	public List<Map<String, Object>> getClxxInf(String ZJH) {
		List<Map<String, Object>> list = new ArrayList();
		if (!StringUtils.isEmpty(ZJH)) 
		{
			if(ZJH.length()==18)
			{
				list = jdbcTemplate.queryForList("select * from JJ_VEHICLE where SFZMHM=?", ZJH);
			}else {
				list = jdbcTemplate.queryForList("select * from JJ_VEHICLE where HPHM=?", ZJH);
			}
		}
		return LongToStringUntils.mapToString(list);
	}
	
    //通过车牌号+身份证号查询机动车信息
	public List<Map<String, Object>> getClxxInf(String SFZH, String HPHM) {
		List<Map<String, Object>> list = new ArrayList();
		if (!StringUtils.isEmpty(SFZH)&&!StringUtils.isEmpty(HPHM)) 
		{
			list = jdbcTemplate.queryForList("select * from JJ_VEHICLE where SFZMHM=? and HPHM=?", SFZH,HPHM);
		}
		return LongToStringUntils.mapToString(list);
	}
	//驾驶证号+好牌号码查询机动车违章信息
	public Map<String, Object> getWzxxInf(String JSZH, String HPHM,Integer pagenow) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList();
		Map<String,Object> map=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(JSZH) && !StringUtils.isEmpty(HPHM) && !StringUtils.isEmpty(pagenow))
		{
			Integer startpage=(pagenow-1)*20;
			Integer endpage=pagenow*20;
			list=jdbcTemplatex.queryForList("select * from (select t.*,rownum rn from  (select * from JJ_VIO_VIOLATION where JSZH=? and HPHM=? order by WFSJ desc) t where rownum<?) where rn>=?",JSZH,HPHM,endpage,startpage);
			Integer total = jdbcTemplatex.queryForObject("select count(*) con from JJ_VIO_VIOLATION where JSZH=? and HPHM=?", Integer.class,JSZH,HPHM);
			map.put("total", total);
			map.put("rows", list);
		}
		return map;
	}

}
