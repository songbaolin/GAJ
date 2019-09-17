package com.inspur.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class LongToStringUntils {

	public static List<Map<String, Object>> mapToString(List<Map<String, Object>> list)
	{
		for(Map<String,Object> map:list)
		{
			for(String key:map.keySet())
			{
				Object vv=map.get(key);
				if(!StringUtils.isEmpty(vv))
				{
					if(key.equals("HHID"))
					{
						System.out.println("HHID"+vv.getClass());
					}
					if(vv instanceof Long || vv instanceof Integer || vv instanceof BigDecimal)
					{
						map.put(key, vv.toString());
					}
				}
			}
		}
		return list;
	}
}
