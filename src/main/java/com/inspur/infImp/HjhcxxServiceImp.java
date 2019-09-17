package com.inspur.infImp;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.inspur.inf.HjhcxxInf;
import com.inspur.utils.Base64Encoding;
import com.inspur.utils.LongToStringUntils;

import oracle.sql.BLOB;
@Service
public class HjhcxxServiceImp implements HjhcxxInf {

	//第一个数据源
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate = null;

	//第二个数据源
	@Autowired
	@Qualifier("jdbcTemplatex")
	private JdbcTemplate jdbcTemplatex = null;
	
	// 人口户籍信息核查
	public List<Map<String, Object>> getSfZGetHjxx(String SFZH) {
		// TODO Auto-generated method stub
		
		List<Map<String, Object>> list = new ArrayList();
		if (!StringUtils.isEmpty(SFZH)) {
			list = jdbcTemplate.queryForList(
					"select * from HJXX_CZRKJBXXB where jlbz='1' and cxbz='0' and  GMSFHM=?", SFZH);
		}
		return LongToStringUntils.mapToString(list);
	}

	// 人员照片信息核查
	public List<Map<String, Object>> getRyzpxx(String SFZH) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (!StringUtils.isEmpty(SFZH)) {
			list = jdbcTemplate.queryForList("select t2.GMSFHM,t2.XM,t2.ZP,t2.LRRQ from HJXX_CZRKJBXXB t,HZ_HJXX_RYZPXXB t2  where t.jlbz='1' and t.cxbz='0' and  t.GMSFHM=? and t.ZPID=t2.ZPID",SFZH);
			System.out.println("查询到照片数据量:"+list.size());
			for (Map<String, Object> map : list) 
			{
				if(map.get("ZP")==null)
					break;				
				byte[] bytes = (byte[]) map.get("ZP");
			    map.put("ZP", Base64Encoding.base64(bytes));
			}
		}
		return list;
	}

	public int getSfZAndXmGetHjxx(String SFZH, String XM) {
		System.out.println("IMP获取SFZH"+SFZH+"获取姓名:"+XM);
		int count=0;
		if (!StringUtils.isEmpty(SFZH)&&!StringUtils.isEmpty(XM)) {
			
			count = jdbcTemplate.queryForInt(
					"select count(*) con from HJXX_CZRKJBXXB where jlbz='1' and ryzt='0' and cxbz='0' and GMSFHM=? and XM=?", SFZH,XM);
		}
		return count;
	}

	//家庭人员信息核查
	public List<Map<String, Object>> getRyHid(String HHID) {
		List<Map<String, Object>> list = new ArrayList();
		if (!StringUtils.isEmpty(HHID)) {
			list = jdbcTemplate.queryForList(
					"select * from HJXX_CZRKJBXXB where jlbz='1' and cxbz='0' and  HHID=?", HHID);
		}
		return LongToStringUntils.mapToString(list);
	}

	//
	@SuppressWarnings("deprecation")
	public Map<String, Object> getWfryxx(String SFZH)
	{
		Map<String, Object> map=new HashMap<String, Object>();
		String res="否";
		if (!StringUtils.isEmpty(SFZH))
		{
			int con= jdbcTemplatex.queryForInt("select count(*) from xtba_xtba_sary where sfzh=?", SFZH);
			if(con>0)
			{
				res="是";
			}
		}
		map.put("res", res);
		return map;	
	}
}
